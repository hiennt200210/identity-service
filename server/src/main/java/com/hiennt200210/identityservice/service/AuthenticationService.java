package com.hiennt200210.identityservice.service;

import com.hiennt200210.identityservice.dto.request.AuthenticationRequest;
import com.hiennt200210.identityservice.dto.request.IntrospectRequest;
import com.hiennt200210.identityservice.dto.response.AuthenticationResponse;
import com.hiennt200210.identityservice.dto.response.IntrospectResponse;
import com.hiennt200210.identityservice.entity.User;
import com.hiennt200210.identityservice.enums.ErrorCode;
import com.hiennt200210.identityservice.exception.ApiException;
import com.hiennt200210.identityservice.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authenticate a user
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        // Check if user does not exist
        User user = userRepository.findByUsername(request.getUsername())
                                  .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        // Check if password does not match
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ApiException(ErrorCode.UNAUTHORIZED);
        }

        // Generate token
        String token = generateToken(user);

        return new AuthenticationResponse(token);
    }

    /**
     * Generate a token
     */
    private String generateToken(User user) {

        // Create JWS header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        // Build the JWT claims set
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserId())
                .claim("username", user.getUsername())
                .issuer("")
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .build();

        // Create JWS payload
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        // Create JWS object
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            // Apply the HMAC to the JWS object
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            System.out.println("Exception here");
            throw new RuntimeException(e);
        }
    }

    /**
     * Verify token
     */
    public IntrospectResponse introspect(IntrospectRequest request) {

        String token = request.getToken();

        // Verify token
        try {
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            return new IntrospectResponse(signedJWT.verify(verifier) && expiryTime.after(new Date()));
        } catch (JOSEException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

}

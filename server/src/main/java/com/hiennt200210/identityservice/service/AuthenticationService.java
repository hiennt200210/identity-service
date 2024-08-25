package com.hiennt200210.identityservice.service;

import com.hiennt200210.identityservice.dto.request.AuthenticationRequest;
import com.hiennt200210.identityservice.dto.response.AuthenticationResponse;
import com.hiennt200210.identityservice.entity.User;
import com.hiennt200210.identityservice.enums.ErrorCode;
import com.hiennt200210.identityservice.exception.ApiException;
import com.hiennt200210.identityservice.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    protected static final String SIGNED_KEY = "0mTCQkfBt/q2f77skNYspTm4yqsJCqywiOJro3cMhGy564KUR+ou6tvQdPTGcsYC";

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

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserId())
                .claim("username", user.getUsername())
                .issuer("")
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            // Apply the HMAC to the JWS object
            jwsObject.sign(new MACSigner(SIGNED_KEY.getBytes()));

            // Output in URL-safe format
            return jwsObject.serialize();
        } catch (JOSEException e) {
            System.out.println("Exception here");
            throw new RuntimeException(e);
        }
    }
}

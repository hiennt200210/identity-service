package com.hiennt200210.identityservice.service;

import com.hiennt200210.identityservice.dto.request.AuthenticationRequest;
import com.hiennt200210.identityservice.dto.response.AuthenticationResponse;
import com.hiennt200210.identityservice.entity.User;
import com.hiennt200210.identityservice.enums.ErrorCode;
import com.hiennt200210.identityservice.exception.ApiException;
import com.hiennt200210.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    UserRepository userRepository;

    /**
     * Authenticate a user
     */
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        // Check if user does not exist
        String username = authenticationRequest.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        // Verify if the provided password matches the stored hashed password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return new AuthenticationResponse(passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword()));
    }
}

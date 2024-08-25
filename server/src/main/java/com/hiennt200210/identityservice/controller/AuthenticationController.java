package com.hiennt200210.identityservice.controller;

import com.hiennt200210.identityservice.dto.request.AuthenticationRequest;
import com.hiennt200210.identityservice.dto.response.ApiResponse;
import com.hiennt200210.identityservice.dto.response.AuthenticationResponse;
import com.hiennt200210.identityservice.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        ApiResponse<AuthenticationResponse> response = new ApiResponse<>(authenticationService.authenticate(authenticationRequest));
        return ResponseEntity.ok(response);
    }
}

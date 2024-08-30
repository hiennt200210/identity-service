package com.hiennt200210.identityservice.controller;

import com.hiennt200210.identityservice.dto.request.AuthenticationRequest;
import com.hiennt200210.identityservice.dto.request.IntrospectRequest;
import com.hiennt200210.identityservice.dto.response.ApiResponse;
import com.hiennt200210.identityservice.dto.response.AuthenticationResponse;
import com.hiennt200210.identityservice.dto.response.IntrospectResponse;
import com.hiennt200210.identityservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/token")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        ApiResponse<AuthenticationResponse> response = new ApiResponse<>(authenticationService.authenticate(authenticationRequest));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/introspect")
    public ResponseEntity<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request) {
        ApiResponse<IntrospectResponse> response = new ApiResponse<>(authenticationService.introspect(request));
        return ResponseEntity.ok(response);
    }

}

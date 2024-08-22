package com.hiennt200210.identityservice.controller;

import com.hiennt200210.identityservice.dto.request.UserCreateRequest;
import com.hiennt200210.identityservice.dto.request.UserUpdateRequest;
import com.hiennt200210.identityservice.dto.response.ApiResponse;
import com.hiennt200210.identityservice.dto.response.UserResponse;
import com.hiennt200210.identityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        ApiResponse<List<UserResponse>> response = new ApiResponse<>(userService.getAllUsers());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable String userId) {
        ApiResponse<UserResponse> response = new ApiResponse<>(userService.getUserById(userId));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        ApiResponse<UserResponse> response = new ApiResponse<>(userService.createUser(userCreateRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        ApiResponse<UserResponse> response = new ApiResponse<>(userService.updateUser(userId, userUpdateRequest));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse<>());
    }

}

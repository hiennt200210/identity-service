package com.hiennt200210.identityservice.controller;

import com.hiennt200210.identityservice.dto.request.UserCreateDto;
import com.hiennt200210.identityservice.dto.request.UserUpdateDto;
import com.hiennt200210.identityservice.dto.response.ApiResponse;
import com.hiennt200210.identityservice.entity.User;
import com.hiennt200210.identityservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        ApiResponse<User> response = new ApiResponse<>(userService.createUser(userCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        ApiResponse<List<User>> response = new ApiResponse<>(userService.getAllUsers());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable String userId) {
        ApiResponse<User> response = new ApiResponse<>(userService.getUserById(userId));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        ApiResponse<User> response = new ApiResponse<>(userService.updateUser(userId, userUpdateDto));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String userId) {
        ApiResponse<Void> response = new ApiResponse<>("success");
        userService.deleteUser(userId);
        return ResponseEntity.ok(response);
    }
}

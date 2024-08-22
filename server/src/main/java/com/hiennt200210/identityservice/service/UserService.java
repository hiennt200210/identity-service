package com.hiennt200210.identityservice.service;

import com.hiennt200210.identityservice.dto.request.UserCreateRequest;
import com.hiennt200210.identityservice.dto.request.UserUpdateRequest;
import com.hiennt200210.identityservice.dto.response.UserResponse;
import com.hiennt200210.identityservice.entity.User;
import com.hiennt200210.identityservice.enums.ErrorCode;
import com.hiennt200210.identityservice.exception.ApiException;
import com.hiennt200210.identityservice.mapper.UserMapper;
import com.hiennt200210.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    /**
     * Get all users
     */
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(userMapper::toUserResponse)
                    .collect(Collectors.toList());
    }

    /**
     * Get a user by ID
     */
    public UserResponse getUserById(String userId) {
        return userMapper.toUserResponse(findUserById(userId));
    }

    /**
     * Create a new user
     */
    public UserResponse createUser(UserCreateRequest userCreateRequest) {

        // Check for duplicate username
        if (userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new ApiException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        // Check for duplicate email
        if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
            throw new ApiException(ErrorCode.EMAIL_ALREADY_IN_USE);
        }

        // Convert UserCreateRequest to User using the mapper
        User user = userMapper.toUser(userCreateRequest);

        // Hashing user password with BCrypt
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));

        // Save new user to database
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(savedUser);
    }

    /**
     * Update a user
     */
    public UserResponse updateUser(String userId, UserUpdateRequest userUpdateRequest) {

        // Find user to update
        User user = findUserById(userId);

        // Update user with data from request
        User updatedUser = userMapper.updateUser(user, userUpdateRequest);

        // Save updated user to database
        User savedUser = userRepository.save(updatedUser);

        return userMapper.toUserResponse(savedUser);
    }

    /**
     * Delete a user
     */
    public void deleteUser(String userId) {

        // Find user to delete
        User user = findUserById(userId);

        // Delete user in database
        userRepository.deleteById(user.getUserId());
    }

    /**
     * Find a user by ID
     */
    public User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
    }

}

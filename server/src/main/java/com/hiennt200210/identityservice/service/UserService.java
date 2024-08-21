package com.hiennt200210.identityservice.service;

import com.hiennt200210.identityservice.dto.request.UserCreateDto;
import com.hiennt200210.identityservice.dto.request.UserUpdateDto;
import com.hiennt200210.identityservice.entity.User;
import com.hiennt200210.identityservice.enums.ErrorCode;
import com.hiennt200210.identityservice.exception.ApiException;
import com.hiennt200210.identityservice.mapper.UserMapper;
import com.hiennt200210.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreateDto userCreateDto) {

        if (userRepository.existsByUsername(userCreateDto.getUsername())) {
            throw new ApiException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw new ApiException(ErrorCode.EMAIL_ALREADY_IN_USE);
        }

        User user = userMapper.toUser(userCreateDto);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
    }

    public User updateUser(String userId, UserUpdateDto userUpdateDto) {
        if (getUserById(userId) == null) {
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        }

        User user = getUserById(userId);
        userMapper.updateUser(user, userUpdateDto);

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        if (getUserById(userId) == null) {
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(userId);
    }
}

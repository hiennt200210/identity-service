package com.hiennt200210.identityservice.service;

import com.hiennt200210.identityservice.dto.UserCreateDto;
import com.hiennt200210.identityservice.dto.UserUpdateDto;
import com.hiennt200210.identityservice.entity.User;
import com.hiennt200210.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreateDto userCreateDto) {

        User user = new User();
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setGender(userCreateDto.getGender());
        user.setDateOfBirth(userCreateDto.getDateOfBirth());
        user.setEmail(userCreateDto.getEmail());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(String userId, UserUpdateDto userUpdateDto) {
        if (getUserById(userId) == null) {
            return null;
        }

        User user = getUserById(userId);
        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        user.setGender(userUpdateDto.getGender());
        user.setDateOfBirth(userUpdateDto.getDateOfBirth());
        user.setEmail(userUpdateDto.getEmail());
        user.setPassword(userUpdateDto.getPassword());

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        if (getUserById(userId) == null) {
            return;
        }
        userRepository.deleteById(userId);
    }
}

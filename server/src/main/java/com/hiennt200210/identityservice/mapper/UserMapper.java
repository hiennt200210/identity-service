package com.hiennt200210.identityservice.mapper;

import com.hiennt200210.identityservice.dto.request.UserCreateRequest;
import com.hiennt200210.identityservice.dto.request.UserUpdateRequest;
import com.hiennt200210.identityservice.dto.response.UserResponse;
import com.hiennt200210.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreateRequest userCreateDto);
    User updateUser(@MappingTarget User user, UserUpdateRequest userUpdateDto);
    UserResponse toUserResponse(User user);

}

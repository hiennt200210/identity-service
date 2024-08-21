package com.hiennt200210.identityservice.mapper;

import com.hiennt200210.identityservice.dto.request.UserCreateDto;
import com.hiennt200210.identityservice.dto.request.UserUpdateDto;
import com.hiennt200210.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateDto userCreateDto);
    void updateUser(@MappingTarget User user, UserUpdateDto userUpdateDto);
}

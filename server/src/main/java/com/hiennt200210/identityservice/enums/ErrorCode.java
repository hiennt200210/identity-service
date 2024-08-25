package com.hiennt200210.identityservice.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum ErrorCode {

    USERNAME_ALREADY_EXISTS(400, "1000", "Bad Request", "Username already exists"),
    EMAIL_ALREADY_IN_USE(400, "1001", "Bad Request", "Email already in use"),
    UNAUTHORIZED(401, "401", "Unauthorized", "Unauthorized"),
    USER_NOT_FOUND(404, "1002", "Not Found", "User not found"),
    INTERNAL_SERVER_ERROR(500, "9000", "Internal Server Error", "Internal Server Error");

    int status;
    String code;
    String title;
    String detail;

}

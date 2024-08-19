package com.hiennt200210.identityservice.enums;

public enum ErrorCode {

    USERNAME_ALREADY_EXISTS(400, "1000", "Bad Request", "Username already exists"),
    EMAIL_ALREADY_EXISTS(400, "1001", "Bad Request", "Email already exists"),
    USER_NOT_FOUND(404, "1002", "Not Found", "User not found"),
    INTERNAL_SERVER_ERROR(500, "9000", "Internal Server Error", "Internal Server Error");

    private final int status;
    private final String code;
    private final String title;
    private final String detail;

    ErrorCode(int status, String code, String title, String detail) {
        this.status = status;
        this.code = code;
        this.title = title;
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}

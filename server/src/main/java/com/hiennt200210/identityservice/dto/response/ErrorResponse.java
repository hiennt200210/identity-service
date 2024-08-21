package com.hiennt200210.identityservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hiennt200210.identityservice.exception.ApiException;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String code;
    private String message;
    private String details;
    private LocalDateTime timestamp;

    public ErrorResponse(ApiException apiException) {
        this.code = apiException.getErrorCode().getCode();
        this.message = apiException.getErrorCode().getTitle();
        this.details = apiException.getErrorCode().getDetail();
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(FieldError fieldError) {
        this.code = "BAD_REQUEST";
        this.message = "Invalid " + fieldError.getField() + " field";
        this.details = fieldError.getDefaultMessage();
        this.timestamp = LocalDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

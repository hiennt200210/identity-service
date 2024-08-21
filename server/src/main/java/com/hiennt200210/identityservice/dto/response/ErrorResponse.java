package com.hiennt200210.identityservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hiennt200210.identityservice.exception.ApiException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    String code;
    String message;
    String details;
    LocalDateTime timestamp;

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
}

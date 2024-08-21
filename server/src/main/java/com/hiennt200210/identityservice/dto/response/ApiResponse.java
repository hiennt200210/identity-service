package com.hiennt200210.identityservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hiennt200210.identityservice.exception.ApiException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    String status;
    T data;
    List<ErrorResponse> errors;

    public ApiResponse(String status) {
        this.status = status;
    }

    public ApiResponse(T data) {
        this.status = "success";
        this.data = data;
    }

    public ApiResponse(List<ErrorResponse> errors) {
        this.status = "error";
        this.errors = errors;
    }
}

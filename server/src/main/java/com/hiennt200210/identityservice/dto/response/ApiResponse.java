package com.hiennt200210.identityservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hiennt200210.identityservice.exception.ApiException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String status;
    private T data;
    private ErrorResponse errors;

    public ApiResponse(String status) {
        this.status = status;
    }

    public ApiResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public ApiResponse(String status, ErrorResponse errors) {
        this.status = status;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(ErrorResponse errors) {
        this.errors = errors;
    }
}

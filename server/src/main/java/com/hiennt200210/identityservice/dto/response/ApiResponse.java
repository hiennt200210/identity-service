package com.hiennt200210.identityservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hiennt200210.identityservice.exception.ApiException;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String status;
    private T data;
    private List<ErrorResponse> errors;

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

    public void setErrors(List<ErrorResponse> errors) {
        this.errors = errors;
    }
}

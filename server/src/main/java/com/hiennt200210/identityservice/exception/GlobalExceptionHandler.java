package com.hiennt200210.identityservice.exception;

import com.hiennt200210.identityservice.dto.response.ApiResponse;
import com.hiennt200210.identityservice.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException exception, WebRequest request) {
        ApiResponse response = new ApiResponse("error", new ErrorResponse(exception));
        return ResponseEntity.status(exception.getErrorCode().getStatus()).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hello, World!");
    }
}

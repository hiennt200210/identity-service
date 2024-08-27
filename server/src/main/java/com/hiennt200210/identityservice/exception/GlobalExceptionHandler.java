package com.hiennt200210.identityservice.exception;

import com.hiennt200210.identityservice.dto.response.ApiResponse;
import com.hiennt200210.identityservice.dto.response.ErrorResponse;
import com.hiennt200210.identityservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException exception) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        errorResponses.add(new ErrorResponse(exception));
        ApiResponse<Void> response = new ApiResponse<>(errorResponses);
        return ResponseEntity.status(exception.getErrorCode().getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ErrorResponse> errorResponses = exception.getBindingResult().getAllErrors().stream()
                .map(error -> new ErrorResponse((FieldError) error))
                .collect(Collectors.toList());
        ApiResponse<Void> response = new ApiResponse<>(errorResponses);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException exception) {

        // For testing
        exception.printStackTrace();

        List<ErrorResponse> errorResponses = new ArrayList<>();
        errorResponses.add(new ErrorResponse(new ApiException(ErrorCode.INTERNAL_SERVER_ERROR)));
        ApiResponse<Void> response = new ApiResponse<>(errorResponses);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}

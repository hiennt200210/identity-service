package com.hiennt200210.identityservice.exception;

import com.hiennt200210.identityservice.enums.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ApiException extends RuntimeException {

    ErrorCode errorCode;

}

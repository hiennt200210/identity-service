package com.hiennt200210.identityservice.dto.response;

import com.hiennt200210.identityservice.exception.ApiException;

public class ErrorResponse {
    private final int status;
    private final String code;
    private final String title;
    private final String detail;

    public ErrorResponse(ApiException apiException) {
        this.status = apiException.getErrorCode().getStatus();
        this.code = apiException.getErrorCode().getCode();
        this.title = apiException.getErrorCode().getTitle();
        this.detail = apiException.getErrorCode().getDetail();
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

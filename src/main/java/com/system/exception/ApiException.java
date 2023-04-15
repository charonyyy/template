package com.system.exception;

public class ApiException extends RuntimeException {
    private ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

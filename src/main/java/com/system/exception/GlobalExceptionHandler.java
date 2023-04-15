package com.system.exception;

import com.system.controller.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.system.exception.ErrorCode.INTERNAL_SERVER_ERROR;

@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData handleException(Exception ex) {
        log.error("未知异常", ex);
        return ResponseData.failed(INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ApiException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData handleException(ApiException ex) {
        log.error("业务异常", ex);
        return ResponseData.failed(ex.getErrorCode());
    }
}

package com.system.controller.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.exception.ErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.system.exception.ErrorCode.UNAUTHORIZED;

@Data
@NoArgsConstructor
public class ResponseData {
    private int status;
    private String code;
    private String message;
    private Object data;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();


    public static ResponseData success(Object data) {
        ResponseData response = new ResponseData();
        response.setStatus(1);
        response.setData(data);
        return response;
    }

    public static ResponseData failed(ErrorCode errorCode) {
        ResponseData response = new ResponseData();
        response.setStatus(0);
        response.setCode(errorCode.getCode());
        response.setCode(errorCode.getCode());
        return response;
    }

    public static ResponseData noAuth() {
        ResponseData response = new ResponseData();
        response.setStatus(0);
        response.setCode(UNAUTHORIZED.getCode());
        response.setCode(UNAUTHORIZED.getMessage());
        return response;
    }
}

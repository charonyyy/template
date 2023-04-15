package com.system.controller.response;


import com.system.common.ErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResponseData {
    private int status;
    private String code;
    private String message;
    private Object data;
    private LocalDateTime timestamp = LocalDateTime.now();


    public static ResponseData success(Object data) {
        ResponseData response = new ResponseData();
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
}

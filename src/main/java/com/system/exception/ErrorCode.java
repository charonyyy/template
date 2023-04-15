package com.system.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    BAD_REQUEST("400", "错误的请求"),
    UNAUTHORIZED("401", "未授权"),
    NO_AUTH("401", "缺少授权"),
    FORBIDDEN("403", "禁止访问"),
    NOT_FOUND("404", "资源未找到"),
    INTERNAL_SERVER_ERROR("500", "内部服务器错误");

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

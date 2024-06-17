package com.sd.common;

import lombok.Getter;

@Getter
public enum StatusCode {

    SUCCESS(200, "请求成功",""),
    NULL_ERROR(401, "请求数据为空",""),
    FAILURE(402, "请求失败",""),
    NO_AUTH(403, "无权限",""),
    SYSTEM_ERROR(500, "系统内部异常","");

    private final int code;

    private final String message;

    private final String description;

    StatusCode(int code, String message,String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

}

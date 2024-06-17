package com.sd.common;


import lombok.Data;

@Data
public class BaseResponse<T> {

    // 状态码
    private int code;
    // 数据
    private T data;
    // 消息
    private String message;
    // 描述
    private String description;

    public BaseResponse(int code, T data, String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, String message,String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
}

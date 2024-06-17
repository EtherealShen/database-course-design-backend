package com.sd.common;

public class ResultsUtils {

    // 成功
    public static <T> BaseResponse<T> success(StatusCode statusCode,T data) {
        return new BaseResponse<>(statusCode.getCode(), data, statusCode.getMessage(),statusCode.getDescription());
    }

    // 失败
    public static <T> BaseResponse<T> failure(StatusCode statusCode) {
        return new BaseResponse<>(statusCode.getCode(),statusCode.getMessage(), statusCode.getDescription());
    }

    // 失败 有具体原因
    public static <T> BaseResponse<T> failure(StatusCode statusCode,String description) {
        return new BaseResponse<>(statusCode.getCode(),statusCode.getMessage(), description);
    }

}

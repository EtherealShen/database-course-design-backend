package com.sd.exception;

import com.sd.common.ErrorCode;
import lombok.Getter;

public class BusinessException {
    @Getter
    // 异常码
    private final int code;

    @Getter
    // 描述
    private final String description;

    public BusinessException(String message, int code, String description) {
        super();
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super();
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super();
        this.code = errorCode.getCode();
        this.description = description;
    }
}

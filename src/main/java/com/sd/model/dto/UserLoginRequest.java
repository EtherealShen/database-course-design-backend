package com.sd.model.dto;


import lombok.Data;

@Data
public class UserLoginRequest {
    // 账号
    private String account;
    // 密码
    private String password;
    // 验证码
    private String code;
}

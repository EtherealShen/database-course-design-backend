package com.sd.model.domain;


import lombok.Data;

@Data
public class UserLoginRequest {
    // 账号
    private String account;
    // 密码
    private String password;
}

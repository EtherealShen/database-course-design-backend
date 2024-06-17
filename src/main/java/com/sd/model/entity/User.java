package com.sd.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    // 用户编号
    @TableId()
    private Long userId;

    // 用户账号
    private String userAccount;

    // 用户密码
    private String userPassword;

    private String UserLevel;
}

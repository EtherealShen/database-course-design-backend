package com.sd.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    // 管理员编号
    @TableId
    @TableField("user_id")
    private int userId;
    // 管理员账号
    @TableField("user_account")
    private String userAccount;

    // 管理员密码
    @TableField("user_password")
    private String userPassword;
}

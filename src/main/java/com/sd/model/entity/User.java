package com.sd.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    // 用户编号
    @TableId()
    private Long id;
    // 用户账号
    private String account;
    // 用户密码
    private String password;
    // 用户权限
    private Integer level;
    // 是否删除
    private Integer is_del;
}

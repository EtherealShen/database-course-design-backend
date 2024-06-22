package com.sd.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Employee {
    // 员工编号
    @TableId
    private Long id;
    // 员工姓名
    private String name;
    // 员工密码
    private String password;
    // 员工级别
    private String level;
    // 员工电话
    private String tel;
    // 员工工资
    private double salary;
    // 备注
    private String remarks;
    // 是否删除
    private Integer is_del;
}

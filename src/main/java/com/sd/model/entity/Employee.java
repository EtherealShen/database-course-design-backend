package com.sd.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Employee {
    // 员工编号
    @TableId
    private Long employeeId;
    // 员工姓名
    private String employeeName;
    // 员工密码
    private String employeePassword;
    // 员工级别
    private String employeeLevel;
    // 员工电话
    private String employeePhone;
    // 员工工资
    private double employeeSalary;
    // 备注
    private String remarks;
}

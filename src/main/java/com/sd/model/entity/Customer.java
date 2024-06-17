package com.sd.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Customer {
    // 客户编号
    @TableId
    private Long customerId;
    // 客户名称
    private String customerName;
    // 客户简称
    private String customerAbbreviation;
    // 地址
    private String address;
    // 公司电话
    private String companyPhone;
    // 邮件
    private String email;
    // 联系人
    private String contactPerson;
    // 联系人电话
    private String contactPhone;
    // 备注
    private String remarks;
}

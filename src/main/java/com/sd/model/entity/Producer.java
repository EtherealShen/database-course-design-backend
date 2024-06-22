package com.sd.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Producer {
    // 客户编号
    @TableId
    private Long id;
    // 客户名称
    private String name;
    // 客户简称
    private String abbreviation;
    // 地址
    private String address;
    // 公司电话
    private String tel;
    // 邮件
    private String email;
    // 联系人
    private String contactPerson;
    // 联系人电话
    private String contactTel;
    // 备注
    private String remarks;
    // 是否删除
    private Integer is_del;
}

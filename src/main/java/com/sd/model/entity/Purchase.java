package com.sd.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Purchase {

    // 采购订单编号
    @TableId
    private Long id;
    // 采购数量
    private Long quantity;
    // 员工编号（外码）
    private Integer employeeId;
    // 采购价格
    private double totalPrice;
    // 采购日期
    private Date time;
    // 备注
    private String remarks;
    // 是否删除
    private Integer is_del;
}

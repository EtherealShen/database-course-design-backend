package com.sd.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Purchase {

    // 采购订单编号
    @TableId
    private int purchaseOrderNumber;
    // 员工编号（外码）
    private String employeeId;
    // 采购数量
    private int purchaseQuantity;
    // 采购价格
    private double totalPurchasePrice;
    // 采购日期
    private Date purchaseDate;
    // 备注
    private String remarks;
}

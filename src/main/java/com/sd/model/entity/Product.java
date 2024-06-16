package com.sd.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Product {
    // 商品编号
    @TableId
    private int productId;
    // 商品名称
    private String productName;
    // 商品单价
    private double unitPrice;
    // 供应商编号（外码）
    private String supplierId;
    // 商品简介
    private String productDescription;
    // 备注
    private String remarks;
}

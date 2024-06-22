package com.sd.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Product {
    // 商品编号
    @TableId
    private Long id;
    // 商品名称
    private String name;
    // 商品单价
    private double unitPrice;
    // 供应商编号（外码）
    private Integer producerId;
    // 商品简介
    private String description;
    // 备注
    private String remarks;
    // 是否删除
    private Integer is_del;
}

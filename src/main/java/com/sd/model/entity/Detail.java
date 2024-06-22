package com.sd.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Detail {
    // 明细号
    @TableId()
    private Integer id;
    // 采购数量
    private Long quantity;
    // 采购价格
    private double totalPrice;
    // 采购订单编号
    private Long purchaseId;
    // 产品编号
    private Long productId;
    // 备注
    private String remarks;
    // 是否删除
    private Integer is_del;
}

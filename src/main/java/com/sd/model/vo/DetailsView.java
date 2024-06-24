package com.sd.model.vo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class DetailsView {
    @TableId
    private Long detailId;
    private Long purchaseId;
    private Long productId;
    private Long quantity;
    private double unitPrice;
    private double totalPrice;
    private String remarks;
}

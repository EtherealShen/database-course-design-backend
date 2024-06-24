package com.sd.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TotalData {
    private Long employeeNum;
    private Long producerNum;
    private Long productNum;
    private Long purchaseNum;
    private Long detailNum;
    private BigDecimal totalExpend;
}

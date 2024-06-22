package com.sd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sd.model.entity.Purchase;

import java.util.Map;

public interface PurchaseService extends IService<Purchase> {
    Map purchasePage(Integer pageNum, Integer pageSize, String query);
}

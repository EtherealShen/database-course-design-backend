package com.sd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sd.mapper.PurchaseMapper;
import com.sd.model.entity.Purchase;
import com.sd.service.PurchaseService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements PurchaseService {
}

package com.sd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sd.mapper.PurchaseMapper;

import com.sd.model.entity.Purchase;
import com.sd.service.PurchaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements PurchaseService {
    @Resource
    private PurchaseMapper purchaseMapper;

    @Override
    public Map purchasePage(Integer pageNum, Integer pageSize, String query) {
        Page<Purchase> purchasePage;
        if(!query.isEmpty()){
            QueryWrapper<Purchase> prurchaseQueryWrapper = new QueryWrapper<>();
            prurchaseQueryWrapper.like("name",query);
            purchasePage = purchaseMapper.selectPage(new Page<>(pageNum, pageSize), prurchaseQueryWrapper);
        }else {
            purchasePage = purchaseMapper.selectPage(new Page<>(pageNum, pageSize), null);
        }
        List<Purchase> records = purchasePage.getRecords();
        long total = purchasePage.getTotal();
        Map map = new HashMap();
        map.put("records",records);
        map.put("total",total);
        return map;
    }
}

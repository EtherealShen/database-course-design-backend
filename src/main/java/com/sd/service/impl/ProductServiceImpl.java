package com.sd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sd.mapper.ProductMapper;
import com.sd.model.entity.Product;
import com.sd.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Override
    public Map productPage(Integer pageNum, Integer pageSize, String query) {
        Page<Product> productPage;
        if(!query.isEmpty()){
            QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
            productQueryWrapper.like("name",query);
            productPage = productMapper.selectPage(new Page<>(pageNum, pageSize), productQueryWrapper);
        }else {
            productPage = productMapper.selectPage(new Page<>(pageNum, pageSize), null);
        }
        List<Product> records = productPage.getRecords();
        long total = productPage.getTotal();
        Map map = new HashMap();
        map.put("records",records);
        map.put("total",total);
        return map;
    }
}

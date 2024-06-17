package com.sd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sd.mapper.ProductMapper;
import com.sd.model.entity.Product;
import com.sd.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}

package com.sd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sd.model.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface ProductService extends IService<Product> {
    Map productPage(Integer pageNum, Integer pageSize, String query);
}

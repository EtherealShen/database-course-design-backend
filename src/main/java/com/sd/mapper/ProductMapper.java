package com.sd.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.model.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}

package com.sd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.model.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}

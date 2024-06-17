package com.sd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.model.entity.Purchase;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapper extends BaseMapper<Purchase> {
}

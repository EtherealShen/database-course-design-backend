package com.sd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.model.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
}

package com.sd.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.model.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;


@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}

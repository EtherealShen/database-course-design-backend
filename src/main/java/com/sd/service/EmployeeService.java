package com.sd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sd.model.entity.Employee;

import java.util.Map;

public interface EmployeeService extends IService<Employee> {
    Map employeePage(Integer pageNum,Integer pageSize,String query);
}

package com.sd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sd.model.entity.Employee;


import java.util.List;

public interface EmployeeService extends IService<Employee> {
    public List<Employee> getAllEmployees();
}

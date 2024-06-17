package com.sd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sd.mapper.EmployeeMapper;
import com.sd.model.entity.Employee;
import com.sd.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    public List<Employee> getAllEmployees(){
        return employeeMapper.selectList(null);
    }
}

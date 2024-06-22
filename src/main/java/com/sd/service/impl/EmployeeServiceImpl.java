package com.sd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sd.mapper.EmployeeMapper;
import com.sd.model.entity.Employee;
import com.sd.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Map employeePage(Integer pageNum, Integer pageSize,String query) {
        Page<Employee> employeePage;
        if(!query.isEmpty()){
            QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
            employeeQueryWrapper.like("employee_name",query);
            employeePage = employeeMapper.selectPage(new Page<>(pageNum, pageSize), employeeQueryWrapper);
        }else {
            employeePage = employeeMapper.selectPage(new Page<>(pageNum, pageSize), null);
        }
        List<Employee> records = employeePage.getRecords();
        long total = employeePage.getTotal();
        Map map = new HashMap();
        map.put("records",records);
        map.put("total",total);
        return map;
    }
}

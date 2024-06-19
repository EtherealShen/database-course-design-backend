package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.model.entity.Employee;
import com.sd.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Api(tags = "员工接口")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @ApiOperation("新增员工接口")
    @PostMapping()
    public boolean saveEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @ApiOperation("删除员工接口")
    @DeleteMapping ("{id}")
    public boolean delEmployee(@PathVariable("id") Long id){
        return employeeService.removeById(id);
    }

    @ApiOperation("查询员工接口")
    @GetMapping ("{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id){
        return employeeService.getById(id);
    }

    @ApiOperation("批量查询员工接口")
    @GetMapping ()
    public List<Employee> getEmployeeByIds(@ApiParam("多个id") @RequestParam ("ids") List<Long> ids){
        return employeeService.listByIds(ids);
    }

    @ApiOperation("查询所有员工接口")
    @GetMapping ("all")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployees();
    }

    @ApiOperation("修改员工接口")
    @PutMapping()
    public Employee updateEmployee(@RequestBody Employee employee){
        UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("employee_id", employee.getEmployeeId());
        if(employeeService.update(employee,updateWrapper)){
            return employeeService.getById(employee.getEmployeeId());
        };
        return null;
    }
}

package com.sd.controller;


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

    @ApiOperation("新增用户接口")
    @PostMapping()
    public boolean saveEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @ApiOperation("删除用户接口")
    @DeleteMapping ("{id}")
    public boolean delEmployee(@PathVariable("id") Long id){
        return employeeService.removeById(id);
    }

    @ApiOperation("查询用户接口")
    @GetMapping ("{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id){
        return employeeService.getById(id);
    }

    @ApiOperation("批量查询用户接口")
    @GetMapping ()
    public List<Employee> getEmployeeByIds(@ApiParam("多个id") @RequestParam ("ids") List<Long> ids){
        return employeeService.listByIds(ids);
    }

    @ApiOperation("批量查询用户接口")
    @GetMapping ("all")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployees();
    }
}

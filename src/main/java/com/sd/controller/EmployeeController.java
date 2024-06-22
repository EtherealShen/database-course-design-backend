package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.common.Response;
import com.sd.model.entity.Employee;
import com.sd.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@Api(tags = "员工接口")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @ApiOperation("新增员工接口")
    @PostMapping()
    public Response saveEmployee(@RequestBody Employee employee){
        if(employeeService.save(employee)){
            return Response.success();
        }
        return Response.error();
    }

    @ApiOperation("删除员工接口")
    @DeleteMapping ()
    public Response delEmployee(@RequestParam("id") Long id){
        if(employeeService.removeById(id)){
            return Response.success();
        }
        return Response.error();
    }

    @ApiOperation("查询员工接口")
    @GetMapping ("{id}")
    public Response getEmployeeById(@PathVariable("id") Long id){
        return Response.success(employeeService.getById(id));
    }

    @ApiOperation("修改员工接口")
    @PutMapping()
    public Response updateEmployee(@RequestBody Employee employee){
        UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", employee.getId());
        if(employeeService.update(employee,updateWrapper)){
            return Response.success(employeeService.getById(employee.getId()));
        };
        return Response.error("用户不存在");
    }

    @ApiOperation("分页查询员工接口")
    @GetMapping ()
    public Response getEmployeeList(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize,@RequestParam("query") String query){
        Map map = employeeService.employeePage(pageNum, pageSize,query);
        long total = (long) map.get("total");
        return total>0?Response.success(map):Response.error("数据不足");
    }
}

package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.common.CsvExportUtil;
import com.sd.common.Response;
import com.sd.mapper.EmployeeMapper;
import com.sd.model.entity.Detail;
import com.sd.model.entity.Employee;
import com.sd.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "员工接口")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private EmployeeMapper employeeMapper;

    @ApiOperation("新增员工接口")
    @PostMapping()
    public Response saveEmployee(@RequestBody Employee employee){
        try {
            if(employeeService.save(employee)){
                return Response.success();
            }
        } catch (Exception e) {
            return Response.error("该编号员工已存在！！！");
        }
        return Response.error();
    }

    @ApiOperation("删除员工接口")
    @DeleteMapping ()
    public Response delEmployee(@RequestParam("id") Long id){
        try {
            if (employeeService.removeById(id)) {
                return Response.success();
            }
        } catch (Exception e) {
            return Response.error("数据删除失败，采购主表有相关数据关联！！！");
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

    @ApiOperation("导出成csv文件")
    @GetMapping("/export")
    public void exportToCsv(HttpServletResponse response) {
        try {
            List<Employee> employees = employeeMapper.selectList(null);
            // 转换数据类型
            List<Map<String, Object>> dataMapList = new ArrayList<>();
            for (Employee employee : employees) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("id", employee.getId());
                dataMap.put("name", employee.getName());
                dataMap.put("password",employee.getPassword());
                dataMap.put("level", employee.getLevel());
                dataMap.put("tel", employee.getTel());
                dataMap.put("salary", employee.getSalary());
                dataMap.put("remarks", employee.getRemarks());
                dataMapList.add(dataMap);
            }
            /*
             * 构造导出数据结构
             */
            String titles = "编号,姓名,密码,级别,电话,薪资,备注"; // 设置表头
            // 设置每列字段
            String keys = "id,name,password,level,tel,salary,remarks";
            // 设置响应头
            CsvExportUtil.responseSetProperties("employee", response);
            // 获取响应输出流
            OutputStream outputStream = response.getOutputStream();
            // 执行导出
            CsvExportUtil.doExport(dataMapList, titles, keys, outputStream);
            // 关闭输出流
            outputStream.close();
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
}

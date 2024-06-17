package com.sd.controller;


import com.sd.model.entity.Customer;

import com.sd.model.entity.Employee;
import com.sd.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Api(tags = "客户接口")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @ApiOperation("新增客户接口")
    @PostMapping()
    public boolean saveCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @ApiOperation("删除客户接口")
    @DeleteMapping ("{id}")
    public boolean delCustomer(@PathVariable("id") Long id){
        return customerService.removeById(id);
    }

    @ApiOperation("查询客户接口")
    @GetMapping ("{id}")
    public Customer getCustomerById(@PathVariable("id") Long id){
        return customerService.getById(id);
    }

    @ApiOperation("批量查询客户接口")
    @GetMapping ()
    public List<Customer> getCustomerByIds(@ApiParam("多个id") @RequestParam ("ids") List<Long> ids){
        return customerService.listByIds(ids);
    }

    @ApiOperation("批量查询用户接口")
    @GetMapping ("all")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

}

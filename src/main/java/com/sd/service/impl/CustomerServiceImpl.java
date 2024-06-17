package com.sd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sd.mapper.CustomerMapper;
import com.sd.model.entity.Customer;
import com.sd.service.CustomerService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;
    public List<Customer> getAllCustomer(){
        return customerMapper.selectList(null);
    };
}

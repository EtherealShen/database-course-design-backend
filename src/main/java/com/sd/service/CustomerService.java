package com.sd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sd.model.entity.Customer;

import java.util.List;

public interface CustomerService extends IService<Customer> {
    public List<Customer> getAllCustomer();
}


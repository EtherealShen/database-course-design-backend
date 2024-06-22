package com.sd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sd.model.entity.Producer;

import java.util.List;
import java.util.Map;

public interface ProducerService extends IService<Producer> {
    Map producerPage(Integer pageNum, Integer pageSize, String query);
}


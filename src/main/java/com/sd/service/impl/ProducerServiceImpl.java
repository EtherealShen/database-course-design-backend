package com.sd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sd.mapper.ProducerMapper;
import com.sd.model.entity.Producer;
import com.sd.service.ProducerService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProducerServiceImpl extends ServiceImpl<ProducerMapper, Producer> implements ProducerService {

    @Resource
    private ProducerMapper producerMapper;

    @Override
    public Map producerPage(Integer pageNum, Integer pageSize, String query) {
        Page<Producer> producerPage;
        if(!query.isEmpty()){
            QueryWrapper<Producer> producerQueryWrapper = new QueryWrapper<>();
            producerQueryWrapper.like("name",query);
            producerPage = producerMapper.selectPage(new Page<>(pageNum, pageSize), producerQueryWrapper);
        }else {
            producerPage = producerMapper.selectPage(new Page<>(pageNum, pageSize), null);
        }
        List<Producer> records = producerPage.getRecords();
        long total = producerPage.getTotal();
        Map map = new HashMap();
        map.put("records",records);
        map.put("total",total);
        return map;
    }
}

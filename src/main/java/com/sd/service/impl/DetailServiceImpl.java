package com.sd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sd.mapper.DetailMapper;
import com.sd.model.entity.Detail;
import com.sd.service.DetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DetailServiceImpl extends ServiceImpl<DetailMapper, Detail> implements DetailService {

    @Resource
    private DetailMapper detailMapper;
    @Override
    public Map detailPage(Integer pageNum, Integer pageSize, String query) {
        Page<Detail> detailPage;
        if(!query.isEmpty()){
            QueryWrapper<Detail> detailQueryWrapper = new QueryWrapper<>();
            detailQueryWrapper.like("id",query);
            detailPage = detailMapper.selectPage(new Page<>(pageNum, pageSize), detailQueryWrapper);
        }else {
            detailPage = detailMapper.selectPage(new Page<>(pageNum, pageSize), null);
        }
        List<Detail> records = detailPage.getRecords();
        long total = detailPage.getTotal();
        Map map = new HashMap();
        map.put("records",records);
        map.put("total",total);
        return map;
    }
}

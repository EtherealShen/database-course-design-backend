package com.sd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sd.mapper.DetailsViewMapper;
import com.sd.model.vo.DetailsView;
import com.sd.service.DetailsViewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DetailsViewServiceImpl extends ServiceImpl<DetailsViewMapper, DetailsView> implements DetailsViewService {
    @Resource
    private DetailsViewMapper detailsViewMapper;
    @Override
    public Map detailsViewPage(Integer pageNum, Integer pageSize, String query) {
        Page<DetailsView> detailsViewPage;
        if(!query.isEmpty()){
            QueryWrapper<DetailsView> detailQueryWrapper = new QueryWrapper<>();
            detailQueryWrapper.like("detail_id",query);
            detailsViewPage = detailsViewMapper.selectPage(new Page<>(pageNum, pageSize), detailQueryWrapper);
        }else {
            detailsViewPage = detailsViewMapper.selectPage(new Page<>(pageNum, pageSize), null);
        }
        List<DetailsView> records = detailsViewPage.getRecords();
        long total = detailsViewPage.getTotal();
        Map map = new HashMap();
        map.put("records",records);
        map.put("total",total);
        return map;
    }
}

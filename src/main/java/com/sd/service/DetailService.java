package com.sd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sd.model.entity.Detail;

import java.util.Map;

public interface DetailService extends IService<Detail> {
    Map detailPage(Integer pageNum, Integer pageSize, String query);
}

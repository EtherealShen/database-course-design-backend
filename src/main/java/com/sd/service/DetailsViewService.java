package com.sd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sd.model.vo.DetailsView;

import java.util.Map;

public interface DetailsViewService extends IService<DetailsView> {
    Map detailsViewPage(Integer pageNum, Integer pageSize, String query);
}

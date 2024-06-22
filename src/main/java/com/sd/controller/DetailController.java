package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.common.Response;
import com.sd.model.entity.Detail;
import com.sd.service.DetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "订单明细接口")
@RestController
@RequestMapping("/detail")
public class DetailController {

    @Resource
    private DetailService detailService;

    @ApiOperation("新增订单明细接口")
    @PostMapping()
    public Response saveDetail(@RequestBody Detail detail){
        if(detailService.save(detail)){
            return Response.success();
        }
        return Response.error();
    }

    @ApiOperation("新增订单明细接口")
    @DeleteMapping()
    public Response delDetail(@RequestParam("id") Long id){
        if(detailService.removeById(id)){
            return Response.success();
        }
        return Response.error();
    }

    @ApiOperation("查询订单明细接口")
    @GetMapping ("{id}")
    public Response getDetailById(@PathVariable("id") Long id){
        return Response.success(detailService.getById(id));
    }

    @ApiOperation("修改订单明细接口")
    @PutMapping()
    public Response updateDetail(@RequestBody Detail detail){
        UpdateWrapper<Detail> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", detail.getId());
        if(detailService.update(detail,updateWrapper)){
            return Response.success(detailService.getById(detail.getId()));
        };
        return Response.error("明细表不存在");
    }

    @ApiOperation("分页查询员工接口")
    @GetMapping ()
    public Response getDetailList(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize,@RequestParam("query") String query){
        Map map = detailService.detailPage(pageNum, pageSize,query);
        long total = (long) map.get("total");
        return total>0?Response.success(map):Response.error("数据不足");
    }
}

package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.common.Response;
import com.sd.model.entity.Producer;
import com.sd.service.ProducerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Api(tags = "客户接口")
@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Resource
    private ProducerService producerService;

    @ApiOperation("新增客户接口")
    @PostMapping()
    public Response saveProducer(@RequestBody Producer producer){
        if(producerService.save(producer)){
            return Response.success();
        }
        return Response.error();
    }

    @ApiOperation("删除客户接口")
    @DeleteMapping ()
    public Response delProducer(@RequestParam("id") Long id){
        try {
            if(producerService.removeById(id)){
                return Response.success();
            }
        }catch (Exception e){
            return Response.error("数据删除失败，产品表相关数据关联！！！");
        }
        return Response.error();
    }

    @ApiOperation("查询客户接口")
    @GetMapping ("{id}")
    public Response getProducerById(@PathVariable("id") Long id){
        return Response.success(producerService.getById(id));
    }

    @ApiOperation("修改客户接口")
    @PutMapping()
    public Response updateProducer(@RequestBody Producer producer){
        UpdateWrapper<Producer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", producer.getId());
        if(producerService.update(producer,updateWrapper)){
            return Response.success(producerService.getById(producer.getId()));
        };
        return Response.error("用户不存在");
    }

    @ApiOperation("分页查询客户接口")
    @GetMapping ()
    public Response getProducerList(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize,@RequestParam("query") String query){
        Map map = producerService.producerPage(pageNum, pageSize,query);
        long total = (long) map.get("total");
        return total>0?Response.success(map):Response.error("数据不足");
    }
}

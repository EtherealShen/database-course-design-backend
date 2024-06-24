package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.common.CsvExportUtil;
import com.sd.common.Response;
import com.sd.mapper.ProducerMapper;
import com.sd.model.entity.Employee;
import com.sd.model.entity.Producer;
import com.sd.service.ProducerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "客户接口")
@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Resource
    private ProducerService producerService;

    @Resource
    private ProducerMapper producerMapper;

    @ApiOperation("新增客户接口")
    @PostMapping()
    public Response saveProducer(@RequestBody Producer producer){
        try {
            if(producerService.save(producer)){
                return Response.success();
            }
        } catch (Exception e) {
            return Response.error("该编号客户已存在！！！");
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

    @ApiOperation("导出成csv文件")
    @GetMapping("/export")
    public void exportToCsv(HttpServletResponse response) {
        try {
            List<Producer> producers = producerMapper.selectList(null);
            // 转换数据类型
            List<Map<String, Object>> dataMapList = new ArrayList<>();
            for (Producer producer : producers) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("id", producer.getId());
                dataMap.put("name", producer.getName());
                dataMap.put("abbreviation",producer.getAbbreviation());
                dataMap.put("address", producer.getAddress());
                dataMap.put("tel", producer.getTel());
                dataMap.put("contact_person",producer.getContactPerson());
                dataMap.put("contact_tel", producer.getContactTel());
                dataMap.put("remarks", producer.getRemarks());
                dataMapList.add(dataMap);
            }
            /*
             * 构造导出数据结构
             */
            String titles = "编号,姓名,简称,地址,电话,联系人,联系人电话,备注"; // 设置表头
            // 设置每列字段
            String keys = "id,name,abbreviation,address,tel,contact_person,contact_tel,remarks";
            // 设置响应头
            CsvExportUtil.responseSetProperties("producer", response);
            // 获取响应输出流
            OutputStream outputStream = response.getOutputStream();
            // 执行导出
            CsvExportUtil.doExport(dataMapList, titles, keys, outputStream);
            // 关闭输出流
            outputStream.close();
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
}

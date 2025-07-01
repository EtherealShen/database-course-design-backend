package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.common.CsvExportUtil;
import com.sd.common.Response;
import com.sd.mapper.DetailMapper;
import com.sd.mapper.ProductMapper;
import com.sd.model.entity.Detail;
import com.sd.model.entity.Product;
import com.sd.service.DetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "订单明细接口")
@SuppressWarnings("all")
@RestController
@RequestMapping("/detail")
public class DetailController {

    @Resource
    private DetailService detailService;
    @Resource
    private DetailMapper detailMapper;

    @ApiOperation("新增订单明细接口")
    @PostMapping()
    public Response saveDetail(@RequestBody Detail detail){
        System.out.println(detail);
        try {
            if(detailService.save(detail)){
                return Response.success();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Response.error();
    }

    @ApiOperation("删除订单明细接口")
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

    @ApiOperation("分页查询明细表接口")
    @GetMapping ()
    public Response getDetailList(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize,@RequestParam("query") String query){
        Map map = detailService.detailPage(pageNum, pageSize,query);
        long total = (long) map.get("total");
        return total>0?Response.success(map):Response.error("数据不足");
    }

    @ApiOperation("导出成csv文件")
    @GetMapping("/export")
    public void exportToCsv(HttpServletResponse response) {
        try {
            List<Detail> details = detailMapper.selectList(null);
            // 转换数据类型
            List<Map<String, Object>> dataMapList = new ArrayList<>();
            for (Detail detail : details) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("id", detail.getId());
                dataMap.put("quantity", detail.getQuantity());
                dataMap.put("total_price", detail.getTotalPrice());
                dataMap.put("purchase_id", detail.getPurchaseId());
                dataMap.put("product_id", detail.getProductId());
                dataMap.put("remarks", detail.getRemarks());
                dataMapList.add(dataMap);
            }
            /*
             * 构造导出数据结构
             */
            String titles = "编号,数量,总价,采购主表编号,产品编号,备注"; // 设置表头
            // 设置每列字段
            String keys = "id,quantity,total_price,purchase_id,product_id,remarks";
            // 设置响应头
            CsvExportUtil.responseSetProperties("detail", response);
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

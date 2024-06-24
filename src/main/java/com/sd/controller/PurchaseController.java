package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.common.CsvExportUtil;
import com.sd.common.Response;
import com.sd.mapper.PurchaseMapper;
import com.sd.model.entity.Employee;
import com.sd.model.entity.Product;
import com.sd.model.entity.Purchase;

import com.sd.service.PurchaseService;
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


@Api(tags = "采购管理接口")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    @Resource
    private PurchaseMapper purchaseMapper;

    @ApiOperation("新增采购订单接口")
    @PostMapping()
    public Response savePurchase(@RequestBody Purchase purchase){
        try {
            purchase.setTotalPrice(0);
            if(purchaseService.save(purchase)){
                return Response.success();
            }
        } catch (Exception e) {
            return Response.error("该编号采购主表已存在！！！");
        }
        return Response.error();
    }

    @ApiOperation("删除采购订单接口")
    @DeleteMapping ()
    public Response delPurchase(@RequestParam("id") Long id){
        try {
            if(purchaseService.removeById(id)){
                return Response.success();
            }
        } catch (Exception e){
            return Response.error("数据删除失败，采购明细表有相关数据关联！！！");
        }
        return Response.error();
    }

    @ApiOperation("查询采购订单接口")
    @GetMapping ("{id}")
    public Response getPurchaseById(@PathVariable("id") Long id){
        return Response.success(purchaseService.getById(id));
    }

    @ApiOperation("采购订单修改接口")
    @PutMapping()
    public Response updatePurchase(@RequestBody Purchase purchase){
        UpdateWrapper<Purchase> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", purchase.getId());
        if(purchaseService.update(purchase,updateWrapper)){
            return Response.success(purchaseService.getById(purchase.getId()));
        };
        return Response.error("订单不存在");
    }

    @ApiOperation("分页查询订单接口")
    @GetMapping ()
    public Response getPurchaseList(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize,@RequestParam("query") String query){
        Map map = purchaseService.purchasePage(pageNum, pageSize,query);
        long total = (long) map.get("total");
        return total>0?Response.success(map):Response.error("数据不足");
    }

    @ApiOperation("导出成csv文件")
    @GetMapping("/export")
    public void exportToCsv(HttpServletResponse response) {
        try {
            List<Purchase> purchases = purchaseMapper.selectList(null);
            // 转换数据类型
            List<Map<String, Object>> dataMapList = new ArrayList<>();
            for (Purchase purchase : purchases) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("id", purchase.getId());
                dataMap.put("quantity", purchase.getQuantity());
                dataMap.put("total_price",purchase.getTotalPrice());
                dataMap.put("time", purchase.getTime());
                dataMap.put("remarks", purchase.getRemarks());
                dataMapList.add(dataMap);
            }
            /*
             * 构造导出数据结构
             */
            String titles = "编号,数量,总价,时间,备注"; // 设置表头
            // 设置每列字段
            String keys = "id,quantity,total_price,time,remarks";
            // 设置响应头
            CsvExportUtil.responseSetProperties("purchase", response);
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

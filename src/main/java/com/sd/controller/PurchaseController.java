package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.common.Response;
import com.sd.model.entity.Employee;
import com.sd.model.entity.Purchase;

import com.sd.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Api(tags = "采购管理接口")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    @ApiOperation("新增采购订单接口")
    @PostMapping()
    public Response savePurchase(@RequestBody Purchase purchase){
        if(purchaseService.save(purchase)){
            return Response.success();
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

}

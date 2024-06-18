package com.sd.controller;




import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.model.entity.Employee;
import com.sd.model.entity.Purchase;

import com.sd.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Api(tags = "采购管理接口")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;

    @ApiOperation("新增采购订单接口")
    @PostMapping()
    public boolean savePurchase(@RequestBody Purchase purchase){
        return purchaseService.save(purchase);
    }

    @ApiOperation("删除采购订单接口")
    @DeleteMapping ("{id}")
    public boolean delPurchase(@PathVariable("id") Long id){
        return purchaseService.removeById(id);
    }

    @ApiOperation("查询采购订单接口")
    @GetMapping ("{id}")
    public Purchase getPurchaseById(@PathVariable("id") Long id){
        return purchaseService.getById(id);
    }

    @ApiOperation("批量查询采购订单接口")
    @GetMapping ()
    public List<Purchase> getPurchaseByIds(@ApiParam("多个id") @RequestParam ("ids") List<Long> ids){
        return purchaseService.listByIds(ids);
    }

    @ApiOperation("采购订单修改接口")
    @PutMapping()
    public Purchase updatePurchase(@RequestBody Purchase purchase){
        UpdateWrapper<Purchase> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("purchase_id", purchase.getPurchaseId());
        if(purchaseService.update(purchase,updateWrapper)){
            return purchaseService.getById(purchase.getPurchaseId());
        };
        return null;
    }
}

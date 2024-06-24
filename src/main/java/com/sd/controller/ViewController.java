package com.sd.controller;


import com.sd.common.Response;
import com.sd.model.vo.TotalData;
import com.sd.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

@Api(tags = "订单明细视图接口")
@RestController
@RequestMapping()
public class ViewController {

    @Resource
    private DetailsViewService detailsViewService;

    @Resource
    private EmployeeService employeeService;
    @Resource
    private ProducerService producerService;
    @Resource
    private ProductService productService;
    @Resource
    private PurchaseService purchaseService;
    @Resource
    private DetailService detailService;


    @ApiOperation("分页查询明细表试图接口")
    @GetMapping("/detailsView")
    public Response getDetailList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestParam("query") String query){
        Map map = detailsViewService.detailsViewPage(pageNum, pageSize,query);
        long total = (long) map.get("total");
        return total>0?Response.success(map):Response.error("数据不足");
    }

    @ApiOperation("统计数据")
    @GetMapping("/total")
    public Response total(){
        Long employeeNum = employeeService.count();
        Long producerNum = producerService.count();
        Long productNum = productService.count();
        Long purchaseNum = purchaseService.count();
        Long detailNum = detailService.count();
        BigDecimal totalExpend = purchaseService.getTotalPriceSum();
        TotalData totalData = new TotalData(employeeNum,producerNum,productNum,purchaseNum,detailNum,totalExpend);
        ArrayList<TotalData> list = new ArrayList<>();
        list.add(totalData);
        return Response.success(list);
    }
}

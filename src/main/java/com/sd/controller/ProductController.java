package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.common.CsvExportUtil;
import com.sd.common.Response;
import com.sd.mapper.ProducerMapper;
import com.sd.mapper.ProductMapper;
import com.sd.model.entity.Employee;
import com.sd.model.entity.Product;
import com.sd.service.ProductService;
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

@Api(tags = "产品接口")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @Resource
    private ProductMapper productMapper;

    @ApiOperation("新增产品接口")
    @PostMapping()
    public Response saveProduct(@RequestBody Product product){
        try {
            if(productService.save(product)){
                return Response.success();
            }
        } catch (Exception e) {
            return Response.error("该编号产品已存在！！！");
        }
        return Response.error();
    }

    @ApiOperation("删除产品接口")
    @DeleteMapping()
    public Response delProduct(@RequestParam("id") Long id){
        try {
            if(productService.removeById(id)){
                return Response.success();
            }
        }catch (Exception e){
            return Response.error("数据删除失败，采购明细表有相关数据关联！！！");
        }

        return Response.error();
    }

    @ApiOperation("查询产品接口")
    @GetMapping ("{id}")
    public Response getProductById(@PathVariable("id") Long id){
        return Response.success(productService.getById(id));
    }

    @ApiOperation("修改产品接口")
    @PutMapping()
    public Response updateProduct(@RequestBody Product product){
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",product.getId());
        if(productService.update(product,updateWrapper)){
            return Response.success(productService.getById(product.getId()));
        }
        return Response.error("产品不存在");
    }
    @ApiOperation("分页查询产品接口")
    @GetMapping ()
    public Response getProductList(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize,@RequestParam("query") String query){
        Map map = productService.productPage(pageNum, pageSize,query);
        long total = (long) map.get("total");
        return total>0?Response.success(map):Response.error("数据不足");
    }

    @ApiOperation("导出成csv文件")
    @GetMapping("/export")
    public void exportToCsv(HttpServletResponse response) {
        try {
            List<Product> products = productMapper.selectList(null);
            // 转换数据类型
            List<Map<String, Object>> dataMapList = new ArrayList<>();
            for (Product product : products) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("id", product.getId());
                dataMap.put("name", product.getName());
                dataMap.put("unit_price",product.getUnitPrice());
                dataMap.put("description", product.getDescription());
                dataMap.put("remarks", product.getRemarks());
                dataMapList.add(dataMap);
            }
            /*
             * 构造导出数据结构
             */
            String titles = "编号,名称,单价,描述,备注"; // 设置表头
            // 设置每列字段
            String keys = "id,name,unit_price,description,remarks";
            // 设置响应头
            CsvExportUtil.responseSetProperties("product", response);
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

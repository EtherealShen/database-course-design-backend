package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.common.Response;
import com.sd.model.entity.Product;
import com.sd.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "产品接口")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @ApiOperation("新增产品接口")
    @PostMapping()
    public Response saveProduct(@RequestBody Product product){
        if(productService.save(product)){
            return Response.success();
        }
        return Response.error();
    }

    @ApiOperation("删除产品接口")
    @DeleteMapping()
    public Response delProduct(@RequestParam("id") Long id){
        if(productService.removeById(id)){
            return Response.success();
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
}

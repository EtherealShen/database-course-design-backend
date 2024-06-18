package com.sd.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.model.entity.Product;
import com.sd.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "产品接口")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @ApiOperation("新增产品接口")
    @PostMapping()
    public boolean saveProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @ApiOperation("删除产品接口")
    @DeleteMapping("{id}")
    public boolean delProduct(@PathVariable("id") Long id){
        return productService.removeById(id);
    }

    @ApiOperation("查询产品接口")
    @GetMapping ("{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getById(id);
    }

    @ApiOperation("批量查询产品接口")
    @GetMapping ()
    public List<Product> getProductByIds(@ApiParam("多个id") @RequestParam ("ids") List<Long> ids){
        return productService.listByIds(ids);
    }

    @ApiOperation("修改产品接口")
    @PutMapping()
    public Product updateProduct(@RequestBody Product product){
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("product_id",product.getProductId());
        if(productService.update(product,updateWrapper)){
            return productService.getById(product.getProductId());
        }
        return null;
    }
}

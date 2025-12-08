package com.example.mall.controller;

import com.example.mall.common.Result;
import com.example.mall.dto.ProductCreateDTO;
import com.example.mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Result<?> create(@RequestBody ProductCreateDTO dto) {
        productService.createProduct(dto);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> detail(@PathVariable Long id) {
        return Result.success(productService.getProduct(id));
    }

    @GetMapping
    public Result<?> list() {
        return Result.success(productService.listProducts());
    }

    @PutMapping("/{id}/enable")
    public Result<?> enable(@PathVariable Long id) {
        productService.enableProduct(id);
        return Result.success();
    }

    @PutMapping("/{id}/disable")
    public Result<?> disable(@PathVariable Long id) {
        productService.disableProduct(id);
        return Result.success();
    }
}

package com.example.mall.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Product {

    private Long id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private String description;

    private String imageUrl;

    private LocalDateTime createTime;

    private Integer status; // 1 上架，0 下架
}

package com.example.mall.vo;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductVO {

    private Long id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private String description;

    private String imageUrl;

    private Integer status;
}

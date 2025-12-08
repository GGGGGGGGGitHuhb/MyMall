package com.example.mall.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductCreateDTO {

    private String name;

    private BigDecimal price;

    private Integer stock;

    private String description;

    private String imageUrl;
}

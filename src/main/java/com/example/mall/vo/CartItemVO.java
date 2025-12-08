package com.example.mall.vo;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CartItemVO {

    private Long id;

    private Long productId;

    private String productName;

    private String imageUrl;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal totalPrice;
}

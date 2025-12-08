package com.example.mall.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemVO {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}

package com.example.mall.entity;

import lombok.Data;

@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private java.math.BigDecimal price;   // 下单时锁定价格
}

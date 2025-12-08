package com.example.mall.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private Long userId;
    private Long addressId;
    private java.math.BigDecimal totalPrice;
    private Integer status;       // 0=已创建, 1=已取消
    private LocalDateTime createTime;
}

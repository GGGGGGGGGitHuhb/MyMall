package com.example.mall.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderVO {

    private Long id;

    private BigDecimal totalPrice;

    private Integer status;

    private LocalDateTime createTime;

    private List<OrderItemVO> items;
}

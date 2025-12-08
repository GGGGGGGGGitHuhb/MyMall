package com.example.mall.entity;

import java.util.Date;
import lombok.Data;

@Data
public class CartItem {

    private Long id;

    private Long userId;

    private Long productId;

    private Integer quantity;

    private Date createTime;
}

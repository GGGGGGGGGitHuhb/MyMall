package com.example.mall.dto;

import lombok.Data;

@Data
public class AddCartItemDTO {

    private Long productId;

    private Integer quantity;
}

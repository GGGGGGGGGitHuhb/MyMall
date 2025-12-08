package com.example.mall.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Address {

    private Long id;

    private Long userId;

    private String receiverName;

    private String receiverPhone;

    private String fullAddress;

    private LocalDateTime createTime;
}

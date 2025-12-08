package com.example.mall.service;

import com.example.mall.dto.OrderCreateDTO;
import com.example.mall.vo.OrderVO;

public interface OrderService {

    Long createOrder(Long userId, OrderCreateDTO dto);

    OrderVO getOrderDetail(Long userId, Long orderId);

    void cancelOrder(Long userId, Long orderId);
}

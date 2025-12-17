package com.example.mall.service;

import java.util.List;
import com.example.mall.dto.OrderCreateDTO;
import com.example.mall.vo.OrderVO;

public interface OrderService {

    Long createOrder(Long userId, OrderCreateDTO dto);

    List<OrderVO> listOrders(Long userId);

    OrderVO getOrderDetail(Long userId, Long orderId);

    void cancelOrder(Long userId, Long orderId);
}

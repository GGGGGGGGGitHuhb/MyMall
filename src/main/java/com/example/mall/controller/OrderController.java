package com.example.mall.controller;

import java.util.Map;
import com.example.mall.common.Result;
import com.example.mall.dto.OrderCreateDTO;
import com.example.mall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Result<?> create(@RequestAttribute("userId") Long userId,
                         @RequestBody OrderCreateDTO dto) {
        Long orderId = orderService.createOrder(userId, dto);
        return Result.success(Map.of("orderId", orderId));
    }

    @GetMapping("/{id}")
    public Result<?> detail(@RequestAttribute("userId") Long userId,
                         @PathVariable Long id) {
        return Result.success(orderService.getOrderDetail(userId, id));
    }

    @PutMapping("/{id}/cancel")
    public Result<?> cancel(@RequestAttribute("userId") Long userId,
                         @PathVariable Long id) {
        orderService.cancelOrder(userId, id);
        return Result.success();
    }
}


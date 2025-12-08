package com.example.mall.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.example.mall.dto.OrderCreateDTO;
import com.example.mall.entity.CartItem;
import com.example.mall.entity.Order;
import com.example.mall.entity.OrderItem;
import com.example.mall.entity.Product;
import com.example.mall.exception.BizException;
import com.example.mall.mapper.CartItemMapper;
import com.example.mall.mapper.OrderItemMapper;
import com.example.mall.mapper.OrderMapper;
import com.example.mall.mapper.ProductMapper;
import com.example.mall.service.OrderService;
import com.example.mall.vo.OrderItemVO;
import com.example.mall.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final CartItemMapper cartItemMapper;
    private final ProductMapper productMapper;

    private final int ORDER_VALID = 1;
    private final int ORDER_CANCELED = 0;

    @Transactional
    @Override
    public Long createOrder(Long userId, OrderCreateDTO dto) {

        // 1. 查询购物车
        List<CartItem> cartItems = cartItemMapper.findByUserId(userId);
        if (cartItems.isEmpty()) {
            throw new BizException(3001, "购物车为空");
        }

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        // 2. 扣库存 + 构造订单项
        for (CartItem cart : cartItems) {
            Product p = productMapper.findById(cart.getProductId());
            if (p == null) {
                throw new BizException(2001, "商品不存在");
            }

            if (p.getStock() < cart.getQuantity()) {
                throw new BizException(2002, "库存不足");
            }

            // 扣库存
            productMapper.reduceStock(p.getId(), cart.getQuantity());

            // 累计总价
            BigDecimal lineTotal = p.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity()));
            total = total.add(lineTotal);

            // 保存订单项
            OrderItem item = new OrderItem();
            item.setProductId(p.getId());
            item.setQuantity(cart.getQuantity());
            item.setPrice(p.getPrice());
            orderItems.add(item);
        }

        // 3. 生成订单主表
        Order order = new Order();
        order.setUserId(userId);
        order.setAddressId(dto.getAddressId());
        order.setTotalPrice(total);
        order.setStatus(ORDER_VALID);
        orderMapper.insert(order);

        // 获取主键
        Long orderId = order.getId();

        // 4. 保存订单项
        orderItems.forEach(i -> i.setOrderId(orderId));
        orderItemMapper.insertBatch(orderItems);

        // 5. 清空购物车（根据业务决定，我建议清空）
        cartItemMapper.clearByUserId(userId);

        return orderId;
    }

    @Override
    public OrderVO getOrderDetail(Long userId, Long orderId) {
        Order order = orderMapper.findById(orderId);
        if (order == null) {
            throw new BizException(5001, "订单不存在");
        }
        if (!order.getUserId().equals(userId)) {
            throw new BizException(1004, "无权限");
        }

        List<OrderItem> items = orderItemMapper.findByOrderId(orderId);

        OrderVO vo = new OrderVO();
        vo.setId(order.getId());
        vo.setStatus(order.getStatus());
        vo.setTotalPrice(order.getTotalPrice());
        vo.setCreateTime(order.getCreateTime());

        List<OrderItemVO> itemVOs = new ArrayList<>();
        for (OrderItem i : items) {
            Product p = productMapper.findById(i.getProductId());

            OrderItemVO v = new OrderItemVO();
            v.setProductId(i.getProductId());
            v.setProductName(p.getName());
            v.setPrice(i.getPrice());
            v.setQuantity(i.getQuantity());
            itemVOs.add(v);
        }

        vo.setItems(itemVOs);
        return vo;
    }

    @Transactional
    @Override
    public void cancelOrder(Long userId, Long orderId) {
        Order order = orderMapper.findById(orderId);
        if (order == null) {
            throw new BizException(5001, "订单不存在");
        }

        if (!order.getUserId().equals(userId)) {
            throw new BizException(1004, "无权限");
        }

        if (order.getStatus() == ORDER_CANCELED) {
            return; // 已取消就不重复取消
        }

        // 1. 恢复库存
        List<OrderItem> items = orderItemMapper.findByOrderId(orderId);
        for (OrderItem i : items) {
            productMapper.increaseStock(i.getProductId(), i.getQuantity());
        }

        // 2. 修改状态
        orderMapper.updateStatus(orderId, ORDER_CANCELED);
    }
}


package com.example.mall.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import com.example.mall.dto.AddCartItemDTO;
import com.example.mall.dto.UpdateCartItemDTO;
import com.example.mall.entity.CartItem;
import com.example.mall.entity.Product;
import com.example.mall.exception.BizException;
import com.example.mall.mapper.CartItemMapper;
import com.example.mall.mapper.ProductMapper;
import com.example.mall.service.CartService;
import com.example.mall.vo.CartItemVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemMapper cartItemMapper;
    private final ProductMapper productMapper;

    @Override
    public void addItem(Long userId, AddCartItemDTO dto) {

        Product product = productMapper.findById(dto.getProductId());
        if (product == null) {
            throw new BizException(2001, "商品不存在");
        }

        CartItem exist = cartItemMapper.findByUserAndProduct(userId, dto.getProductId());

        if (exist == null) {
            CartItem item = new CartItem();
            item.setUserId(userId);
            item.setProductId(dto.getProductId());
            item.setQuantity(dto.getQuantity());
            cartItemMapper.insert(item);
        } else {
            int newQty = exist.getQuantity() + dto.getQuantity();
            cartItemMapper.updateQuantity(exist.getId(), newQty);
        }
    }

    @Override
    public void updateQuantity(Long userId, Long cartItemId, UpdateCartItemDTO dto) {

        CartItem item = cartItemMapper.findById(cartItemId);
        if (item == null || !item.getUserId().equals(userId)) {
            throw new BizException(3001, "购物车项不存在");
        }
        cartItemMapper.updateQuantity(cartItemId, dto.getQuantity());
    }

    @Override
    public void deleteItem(Long userId, Long cartItemId) {

        CartItem item = cartItemMapper.findById(cartItemId);
        if (item == null || !item.getUserId().equals(userId)) {
            throw new BizException(3001, "购物车项不存在");
        }
        cartItemMapper.delete(cartItemId);
    }

    @Override
    public List<CartItemVO> list(Long userId) {

        List<CartItem> items = cartItemMapper.findByUserId(userId);

        return items.stream().map(item -> {
            Product p = productMapper.findById(item.getProductId());
            CartItemVO vo = new CartItemVO();

            vo.setId(item.getId());
            vo.setProductId(p.getId());
            vo.setProductName(p.getName());
            vo.setImageUrl(p.getImageUrl());
            vo.setPrice(p.getPrice());
            vo.setQuantity(item.getQuantity());
            vo.setTotalPrice(p.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

            return vo;
        }).collect(Collectors.toList());
    }
}

package com.example.mall.service;

import java.util.List;
import com.example.mall.dto.AddCartItemDTO;
import com.example.mall.dto.UpdateCartItemDTO;
import com.example.mall.vo.CartItemVO;

public interface CartService {

    void addItem(Long userId, AddCartItemDTO dto);

    void updateQuantity(Long userId, Long cartItemId, UpdateCartItemDTO dto);

    void deleteItem(Long userId, Long cartItemId);

    List<CartItemVO> list(Long userId);
}

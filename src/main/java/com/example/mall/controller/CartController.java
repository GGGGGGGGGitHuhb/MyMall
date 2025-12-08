package com.example.mall.controller;

import java.util.List;
import com.example.mall.common.Result;
import com.example.mall.dto.AddCartItemDTO;
import com.example.mall.dto.UpdateCartItemDTO;
import com.example.mall.service.CartService;
import com.example.mall.util.JwtUtil;
import com.example.mall.vo.CartItemVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/items")
    public Result<List<CartItemVO>> list(HttpServletRequest request) {
        Long userId = getUserId(request);
        return Result.success(cartService.list(userId));
    }

    @PostMapping("/items")
    public Result<Void> add(HttpServletRequest request,
                            @RequestBody AddCartItemDTO dto) {
        Long userId = getUserId(request);
        cartService.addItem(userId, dto);
        return Result.success();
    }

    @PutMapping("/items/{cartItemId}")
    public Result<Void> update(@PathVariable Long cartItemId,
                               HttpServletRequest request,
                               @RequestBody UpdateCartItemDTO dto) {
        Long userId = getUserId(request);
        cartService.updateQuantity(userId, cartItemId, dto);
        return Result.success();
    }

    @DeleteMapping("/items/{cartItemId}")
    public Result<Void> delete(@PathVariable Long cartItemId,
                               HttpServletRequest request) {
        Long userId = getUserId(request);
        cartService.deleteItem(userId, cartItemId);
        return Result.success();
    }

    private Long getUserId(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return JwtUtil.getUserId(token);
    }
}

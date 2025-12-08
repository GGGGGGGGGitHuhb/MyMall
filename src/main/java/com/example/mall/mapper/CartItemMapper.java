package com.example.mall.mapper;

import java.util.List;
import com.example.mall.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Property;

@Mapper
public interface CartItemMapper {

    CartItem findByUserAndProduct(@Param("userId") Long userId,
                                  @Param("productId") Long productId);

    List<CartItem> findByUserId(Long userId);

    CartItem findById(Long id);

    int insert(CartItem cartItem);

    int updateQuantity(@Param("id") Long id,
                       @Param("quantity") Integer quantity);

    int delete(Long id);

    int clearByUserId(@Param("userId") Long userId);
}

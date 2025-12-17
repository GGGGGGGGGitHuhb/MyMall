package com.example.mall.mapper;

import java.util.List;
import com.example.mall.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    int insert(Order order);   // useGeneratedKeys 回填 id

    Order findById(@Param("id") Long id);

    List<Order> findByUserId(Long userId);

    int updateStatus(@Param("id") Long id,
                     @Param("status") Integer status);
}

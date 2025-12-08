package com.example.mall.mapper;

import java.util.List;
import com.example.mall.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    void insert(Product product);

    Product findById(Long id);

    List<Product> findAll();

    void updateStatus(Long id, Integer status);

    int reduceStock(@Param("id") Long id, @Param("count") Integer count);

    int increaseStock(@Param("id") Long id,  @Param("count") Integer count);
}

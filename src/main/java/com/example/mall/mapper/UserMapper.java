package com.example.mall.mapper;

import com.example.mall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // 根据用户名查询用户 用于登录
    User findByUsername(@Param("username") String username);

    // 插入用户 用于注册
    int insert(User user);
}

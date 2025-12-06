package com.example.mall.service.impl;

import java.time.LocalDateTime;
import com.example.mall.dto.UserLoginDTO;
import com.example.mall.dto.UserRegisterDTO;
import com.example.mall.entity.User;
import com.example.mall.mapper.UserMapper;
import com.example.mall.service.UserService;
import com.example.mall.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static java.time.LocalTime.now;

@Service
public class UserServiceImpl implements UserService {

    // 构造器注入
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserVO register(UserRegisterDTO dto) {

        // 用户名是否已存在
        User exist = userMapper.findByUsername(dto.getUsername());
        if (exist != null) {
            throw new RuntimeException("用户名已存在");
        }

        // 创建用户实体
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);

        // 创建返回对象
        UserVO vo = new UserVO();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setPhone(user.getPhone());
        return vo;
    }

    @Override
    public UserVO login(UserLoginDTO dto) {

        User user = userMapper.findByUsername(dto.getUsername());
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 转换为 VO
        UserVO vo = new UserVO();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setPhone(user.getPhone());
        return vo;
    }
}

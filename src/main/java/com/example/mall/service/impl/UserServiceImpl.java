package com.example.mall.service.impl;

import java.time.LocalDateTime;
import com.example.mall.dto.UserLoginDTO;
import com.example.mall.dto.UserRegisterDTO;
import com.example.mall.entity.User;
import com.example.mall.exception.BizException;
import com.example.mall.exception.ErrorCode;
import com.example.mall.mapper.UserMapper;
import com.example.mall.service.UserService;
import com.example.mall.util.JwtUtil;
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
        throw new BizException(ErrorCode.USER_EXIST.code(), ErrorCode.USER_EXIST.msg());
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
            throw new BizException(ErrorCode.USER_NOT_EXIST.code(), ErrorCode.USER_NOT_EXIST.msg());
        }

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new BizException(ErrorCode.PASSWORD_ERROR.code(), ErrorCode.PASSWORD_ERROR.msg());
        }

        // 转换为 VO
        String token = JwtUtil.generateToken(user.getId(), user.getUsername());
        UserVO vo = new UserVO();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setPhone(user.getPhone());
        vo.setToken(token);
        return vo;
    }
}

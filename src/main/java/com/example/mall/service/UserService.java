package com.example.mall.service;

import com.example.mall.dto.UserLoginDTO;
import com.example.mall.dto.UserRegisterDTO;
import com.example.mall.vo.UserVO;

public interface UserService {

    UserVO register(UserRegisterDTO dto);

    UserVO login(UserLoginDTO dto);
}

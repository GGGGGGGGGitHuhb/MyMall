package com.example.mall.controller;

import com.example.mall.common.Result;
import com.example.mall.dto.UserLoginDTO;
import com.example.mall.dto.UserRegisterDTO;
import com.example.mall.service.UserService;
import com.example.mall.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 注册
    @PostMapping("/register")
    public Result<UserVO> register(@RequestBody UserRegisterDTO dto) {
        UserVO vo = userService.register(dto);
        return Result.success(vo);
    }

    // 登录
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody UserLoginDTO dto) {
        UserVO vo = userService.login(dto);
        return Result.success(vo);
    }
}

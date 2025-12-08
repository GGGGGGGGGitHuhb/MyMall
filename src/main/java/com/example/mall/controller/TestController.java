package com.example.mall.controller;

import com.example.mall.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/v1/test")
    public Result<?> test(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return Result.success("当前登录用户ID: " + userId);
    }
}

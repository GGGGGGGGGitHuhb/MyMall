package com.example.mall.interceptor;

import com.example.mall.exception.BizException;
import com.example.mall.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        // 从请求头获取 token
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            throw new BizException(1003, "未登录");
        }

        // 解析 token
        Long userId = JwtUtil.getUserId(token);
        if (userId == null) {
            throw new BizException(1006, "登录已过期或 token 无效");
        }

        // 保存到 request 范围，下游需要用
        request.setAttribute("userId", userId);
        return true;
    }
}

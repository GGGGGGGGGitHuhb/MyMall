package com.example.mall.exception;

public enum ErrorCode {

    // 成功
    SUCCESS(0, "success"),

    // 用户相关
    USER_EXIST(1001, "用户名已存在"),
    PASSWORD_ERROR(1002, "密码错误"),
    NOT_LOGIN(1003, "未登录"),
    NO_PERMISSION(1004, "无权限"),
    USER_NOT_EXIST(1005, "用户不存在"),

    // 商品相关
    PRODUCT_EXIST(2001, "商品已存在"),
    STOCK_NOT_ENOUGH(2002, "库存不足"),

    // 购物车相关
    CART_EMPTY(3001, "购物车为空"),

    // 地址相关
    ADDRESS_NOT_FOUND(4001, "地址不存在"),

    // 订单相关
    ORDER_NOT_FOUND(5001, "订单不存在");

    private final int code;
    private final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}

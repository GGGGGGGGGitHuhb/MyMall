package com.example.mall.common;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code; // 响应码
    private String message; // 提示信息
    private T data; // 返回数据

    private Result() {}

   public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.setCode(0);
        r.setMessage("success");
        r.setData(data);
        return r;
   }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> error(Integer code, String message) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(null);
        return r;
    }
}

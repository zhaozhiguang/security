package com.zhaozhiguang.component.security.base;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public Result() {

    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result of(Integer code, String message) {
        return new Result(code, message);
    }

    public static <T> Result of(Integer code, String message, T data) {
        return new Result(code, message, data);
    }

    public static Result ok() {
        return ok(null);
    }

    public static <T> Result ok(T data) {
        return new Result(200, "操作成功", data);
    }

    public static Result error() {
        return error(null);
    }

    public static <T> Result error(T data) {
        return new Result(500, "内部错误", null);
    }

    public static Result notFound() {
        return notFound(null);
    }

    public static <T> Result notFound(T data) {
        return new Result(404, "没找到资源", data);
    }

}

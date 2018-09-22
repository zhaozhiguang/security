package com.zhaozhiguang.component.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义未登录错误异常
 * @author zhaozhiguang
 */
public class NotLoginException extends AuthenticationException {

    public NotLoginException() {
        super("没有登录");
    }

    public NotLoginException(String msg) {
        super(msg);
    }
}

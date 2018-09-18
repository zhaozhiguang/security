package com.zhaozhiguang.component.security.support;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义验证码错误异常
 * @author zhaozhiguang
 */
public class IncorrectCaptchaException extends AuthenticationException {

    public IncorrectCaptchaException() {
        super("验证码错误");
    }

    public IncorrectCaptchaException(String msg) {
        super(msg);
    }
}

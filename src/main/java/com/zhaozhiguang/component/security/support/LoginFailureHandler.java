package com.zhaozhiguang.component.security.support;

import com.alibaba.fastjson.JSON;
import com.zhaozhiguang.component.security.base.Result;
import com.zhaozhiguang.component.security.exception.NotLoginException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录失败处理
 * @author zhaozhiguang
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.of(switchCode(e), e.getMessage())));
    }

    private int switchCode(AuthenticationException auth) {
        if(auth==null) return 500;
        if(auth.getClass().isAssignableFrom(NotLoginException.class)) return 401;
        return 500;
    }

}

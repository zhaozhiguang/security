package com.zhaozhiguang.component.security.support;

import com.alibaba.fastjson.JSON;
import com.zhaozhiguang.component.jwt.JWT;
import com.zhaozhiguang.component.jwt.algorithms.Algorithm;
import com.zhaozhiguang.component.security.base.Result;
import com.zhaozhiguang.component.security.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义登录成功处理
 * @author zhaozhiguang
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private DateUtil dateUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Map<String , Object> param = new HashMap<>();
        param.put("user", authentication.getPrincipal());
        String sercet = JWT.create().withExpiresAt(dateUtil.fromLocalDateTime(dateUtil.currentAddMin(30))).withParameters(param).sign(Algorithm.HMAC256("sercet"));
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.of(200, "登录成功", sercet)));
    }

}

package com.zhaozhiguang.component.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.zhaozhiguang.component.jwt.JWT;
import com.zhaozhiguang.component.jwt.algorithms.Algorithm;
import com.zhaozhiguang.component.jwt.interfaces.DecodedJWT;
import com.zhaozhiguang.component.security.entity.JwtAuthenticationToken;
import com.zhaozhiguang.component.security.entity.User;
import com.zhaozhiguang.component.security.exception.NotLoginException;
import com.zhaozhiguang.component.security.properties.SecurityProperties;
import com.zhaozhiguang.component.security.exception.IncorrectCaptchaException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends AbstractAuthenticationProcessingFilter implements InitializingBean {

    private String userNameParam = "username";

    private String passwordParam = "password";

    public void setUsernameParameter(String userNameParam){
        this.userNameParam = userNameParam;
    }

    public void setPasswordParameter(String passwordParam){
        this.passwordParam = passwordParam;
    }

    public String getUsernameParameter(){
        return userNameParam;
    }

    public String getPasswordParameter(){
        return passwordParam;
    }

    @Autowired
    private SecurityProperties securityProperties;

    public LoginFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public void afterPropertiesSet() {

    }

    /**
     * 创建token
     * @param request
     * @param response
     * @return
     */
    private JwtAuthenticationToken createToken(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        return new JwtAuthenticationToken(user, false);
    }

    /**
     * 验证码是否正确
     * @param request
     * @return
     */
    public void verify(HttpServletRequest request) throws IncorrectCaptchaException {
        String expected = request.getParameter("captchaEncrypt");
        //获取用户页面输入的验证码
        String received = request.getParameter("captcha");
        if(received == null || expected  == null) throw new IncorrectCaptchaException();
        try {
            DecodedJWT captcha = JWT.require(Algorithm.HMAC256("captcha")).build().verify(expected);
            if(!received.equalsIgnoreCase(captcha.getSubject())) throw new IncorrectCaptchaException();
        } catch (Exception e) {
            throw new IncorrectCaptchaException();
        }
    }

    /**
     * 校验jwt
     * @param request
     * @return
     */
    private boolean checkJwt(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        try {
            DecodedJWT sercet = JWT.require(Algorithm.HMAC256("sercet")).build().verify(token);
            Authentication user = JSONObject.toJavaObject((JSONObject) sercet.getParameters().get("user"), Authentication.class);
            SecurityContextHolder.getContext().setAuthentication(user);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        if (!this.requiresAuthentication(request, response)) {
            //如果不是登录地址,查询jwt
            checkJwt(request);
            try {
                chain.doFilter(request, response);
            } catch (Exception e) {
                this.unsuccessfulAuthentication(request, response, new NotLoginException());
            }
        } else {

            Authentication authResult;
            try {
                authResult = this.attemptAuthentication(request, response);
                if (authResult == null) {
                    return;
                }
            } catch (InternalAuthenticationServiceException var8) {
                this.unsuccessfulAuthentication(request, response, var8);
                return;
            } catch (AuthenticationException var9) {
                this.unsuccessfulAuthentication(request, response, var9);
                return;
            }
            this.successfulAuthentication(request, response, chain, authResult);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(securityProperties.isEnableCaptcha()) verify(request);
        JwtAuthenticationToken token = createToken(request, response);
        return getAuthenticationManager().authenticate(token);
    }

}

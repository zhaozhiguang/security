package com.zhaozhiguang.component.security.filter;

import com.zhaozhiguang.component.security.entity.JwtAuthenticationToken;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@ConditionalOnMissingBean(LoginFilter.class)
public class LoginFilter extends AbstractAuthenticationProcessingFilter implements InitializingBean {

    public LoginFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public void afterPropertiesSet() {

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken());
    }

}

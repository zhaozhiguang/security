package com.zhaozhiguang.component.security.support;

import com.zhaozhiguang.component.security.filter.LoginFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LoginCustomDsl extends AbstractHttpConfigurer<LoginCustomDsl, HttpSecurity> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ApplicationContext context = http.getSharedObject(ApplicationContext.class);
        LoginFilter myFilter = context.getBean(LoginFilter.class);
        http.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

package com.zhaozhiguang.component.security.config;

import com.zhaozhiguang.component.security.filter.CorsFilter;
import com.zhaozhiguang.component.security.filter.LoginFilter;
import com.zhaozhiguang.component.security.properties.SecurityProperties;
import com.zhaozhiguang.component.security.support.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        super(true);
    }

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        loginFilter.setAuthenticationFailureHandler(loginFailureHandler);
        return loginFilter;
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public LoginCustomDsl loginCustomDsl(){
        return new LoginCustomDsl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(loginCustomDsl());
        http.authorizeRequests().antMatchers("/captcha").permitAll().anyRequest().authenticated();
        http.anonymous();
        http.csrf().disable();
        if(securityProperties.isEnableCors()){
            http.addFilterAfter(corsFilter(), AnonymousAuthenticationFilter.class);
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
}

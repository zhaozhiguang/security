package com.zhaozhiguang.component.security.config;

import com.zhaozhiguang.component.security.filter.LoginFilter;
import com.zhaozhiguang.component.security.support.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        super(true);
    }

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
    };

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public LoginFormConfigurer loginFormConfigurer(){
        return new LoginFormConfigurer();
    }

    @Bean
    public LoginCustomDsl loginCustomDsl(){
        return new LoginCustomDsl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //LoginFormConfigurer loginFormConfigurer = new LoginFormConfigurer();
        //http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        //http.getConfigurer(loginFormConfigurer.getClass());
        http.apply(loginCustomDsl());
        http.authorizeRequests().antMatchers("/captcha").permitAll().anyRequest().authenticated();
        http.anonymous();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
}

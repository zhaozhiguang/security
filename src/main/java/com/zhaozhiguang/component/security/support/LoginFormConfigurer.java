package com.zhaozhiguang.component.security.support;

import com.zhaozhiguang.component.security.filter.LoginFilter;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class LoginFormConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractAuthenticationFilterConfigurer<H, LoginFormConfigurer<H>, LoginFilter> {

    public LoginFormConfigurer() {
        super(new LoginFilter(), null);
        this.usernameParameter("username");
        this.passwordParameter("password");
    }

    @Override
    public void configure(H http) throws Exception {
        //super.configure(http);
    }

    public LoginFormConfigurer<H> loginPage(String loginPage) {
        return super.loginPage(loginPage);
    }

    public LoginFormConfigurer<H> usernameParameter(String usernameParameter) {
        this.getAuthenticationFilter().setUsernameParameter(usernameParameter);
        return this;
    }

    public LoginFormConfigurer<H> passwordParameter(String passwordParameter) {
        this.getAuthenticationFilter().setPasswordParameter(passwordParameter);
        return this;
    }

    public LoginFormConfigurer<H> failureForwardUrl(String forwardUrl) {
        this.failureHandler(new ForwardAuthenticationFailureHandler(forwardUrl));
        return this;
    }

    public LoginFormConfigurer<H> successForwardUrl(String forwardUrl) {
        this.successHandler(new ForwardAuthenticationSuccessHandler(forwardUrl));
        return this;
    }

    public void init(H http) throws Exception {
        super.init(http);
        this.initDefaultLoginFilter(http);
    }

    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
    }

    private String getUsernameParameter() {
            return (this.getAuthenticationFilter()).getUsernameParameter();
    }

    private String getPasswordParameter() {
        return (this.getAuthenticationFilter()).getPasswordParameter();
    }

    private void initDefaultLoginFilter(H http) {
        DefaultLoginPageGeneratingFilter loginPageGeneratingFilter = http.getSharedObject(DefaultLoginPageGeneratingFilter.class);
        if (loginPageGeneratingFilter != null && !this.isCustomLoginPage()) {
            loginPageGeneratingFilter.setFormLoginEnabled(true);
            loginPageGeneratingFilter.setUsernameParameter(this.getUsernameParameter());
            loginPageGeneratingFilter.setPasswordParameter(this.getPasswordParameter());
            loginPageGeneratingFilter.setLoginPageUrl(this.getLoginPage());
            loginPageGeneratingFilter.setFailureUrl(this.getFailureUrl());
            loginPageGeneratingFilter.setAuthenticationUrl(this.getLoginProcessingUrl());
        }

    }
}

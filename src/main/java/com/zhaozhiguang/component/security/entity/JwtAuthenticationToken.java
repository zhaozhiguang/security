package com.zhaozhiguang.component.security.entity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken implements Authentication {

    private User user;

    private boolean authenticated;

    public JwtAuthenticationToken() {

    }

    public JwtAuthenticationToken(User user, boolean authenticated) {
        this.user = user;
        this.authenticated = authenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

}

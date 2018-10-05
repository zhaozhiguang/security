package com.zhaozhiguang.component.security.entity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthenticationToken implements Authentication {

    private UserDetails sysUser;

    private boolean authenticated;

    public JwtAuthenticationToken() {

    }

    public JwtAuthenticationToken(UserDetails sysUser, boolean authenticated) {
        this.sysUser = sysUser;
        this.authenticated = authenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return sysUser.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return sysUser.getPassword();
    }

    @Override
    public Object getDetails() {
        return sysUser;
    }

    @Override
    public Object getPrincipal() {
        return sysUser;
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
        return sysUser.getUsername();
    }

}

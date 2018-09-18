package com.zhaozhiguang.component.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 用户领域对象
 * @author zhaozhiguang
 */
@Data
public class User implements UserDetails {

    private String id;

    private String userName;

    private String password;

    private List<GrantedAuthority> authorities;

    private Integer status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status==4;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status==2;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status==3;
    }

    @Override
    public boolean isEnabled() {
        return status==0;
    }
}

package com.zhaozhiguang.component.security.support;

import com.zhaozhiguang.component.security.entity.JwtAuthenticationToken;
import com.zhaozhiguang.component.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginUserDetailService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(token.getName());
        if(userDetails==null){
            throw new UsernameNotFoundException("用户没找到");
        }
        if(authentication.getCredentials() == null || !bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())){
            throw new BadCredentialsException("密码错误");
        }
        if(userDetails.isAccountNonExpired()){
            throw new AccountExpiredException("账号已过期");
        }
        if(userDetails.isAccountNonLocked()){
            throw new LockedException("账号已锁定");
        }
        if(userDetails.isEnabled()){
            throw new DisabledException("账号已禁用");
        }
        return new JwtAuthenticationToken((User) userDetails, true);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }

}

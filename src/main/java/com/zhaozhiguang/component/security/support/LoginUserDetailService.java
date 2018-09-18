package com.zhaozhiguang.component.security.support;

import com.zhaozhiguang.component.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoginUserDetailService implements UserDetailsService {


    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUserName("test1");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setId(UUID.randomUUID().toString());
        user.setStatus(1);
        return user;
    }

}

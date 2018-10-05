package com.zhaozhiguang.component.security.support;

import com.zhaozhiguang.component.security.entity.SysPermissions;
import com.zhaozhiguang.component.security.entity.SysUser;
import com.zhaozhiguang.component.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.findByUserName(username);
        List<SysPermissions> permissions = userService.findPermissionsByUserId(sysUser.getId());
        List<GrantedAuthority> collect = null;
        if(permissions != null && !permissions.isEmpty())
            collect = permissions.stream().map(sysPermissions -> (GrantedAuthority) sysPermissions).collect(Collectors.toList());
        sysUser.setAuthorities(collect);
        return sysUser;
    }

}

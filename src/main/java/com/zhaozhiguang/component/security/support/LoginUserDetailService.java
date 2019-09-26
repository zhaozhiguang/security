package com.zhaozhiguang.component.security.support;

import com.zhaozhiguang.component.security.entity.SysPermissions;
import com.zhaozhiguang.component.security.entity.SysRole;
import com.zhaozhiguang.component.security.entity.SysUser;
import com.zhaozhiguang.component.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.findByUserName(username);
        Set<SysPermissions> permissions = userService.findPermissionsByUserId(sysUser.getId());
        Set<SysRole> roles = userService.findRolesByUserId(sysUser.getId());
        List<GrantedAuthority> collect = null;
        /*Set<SysRole> roles = sysUser.getRoles();
        if(roles != null && !roles.isEmpty()) {
            collect = roles.stream().flatMap((Function<SysRole, Stream<SysPermissions>>) sysRole -> sysRole.getPermissions().stream()).collect(Collectors.toList());
        }*/
        if (permissions != null && !permissions.isEmpty())
            collect = permissions.stream().map(sysPermissions -> (GrantedAuthority) sysPermissions).collect(Collectors.toList());
        sysUser.setAuthorities(collect);
        sysUser.setRoles(roles);
        sysUser.setPermissions(permissions);
        sysUser.setAuthorities(collect);
        return sysUser;
    }

}

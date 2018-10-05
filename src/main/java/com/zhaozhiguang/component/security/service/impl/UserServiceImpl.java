package com.zhaozhiguang.component.security.service.impl;

import com.zhaozhiguang.component.security.entity.SysPermissions;
import com.zhaozhiguang.component.security.entity.SysRole;
import com.zhaozhiguang.component.security.entity.SysUser;
import com.zhaozhiguang.component.security.repository.PermissionsRepository;
import com.zhaozhiguang.component.security.repository.RoleRepository;
import com.zhaozhiguang.component.security.repository.UserRepository;
import com.zhaozhiguang.component.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionsRepository permissionsRepository;

    @Override
    public SysUser findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<SysRole> findRolesByUserId(Integer userId) {
        return roleRepository.findAllByUserId(userId);
    }

    @Override
    public List<SysPermissions> findPermissionsByUserId(Integer userId) {
        return permissionsRepository.findAllByUserId(userId);
    }
}

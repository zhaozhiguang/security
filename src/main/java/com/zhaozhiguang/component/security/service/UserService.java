package com.zhaozhiguang.component.security.service;

import com.zhaozhiguang.component.security.entity.SysPermissions;
import com.zhaozhiguang.component.security.entity.SysRole;
import com.zhaozhiguang.component.security.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserService {

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    SysUser findByUserName (String userName);

    /**
     * 查询用户的角色列表
     * @param userId
     * @return
     */
    Set<SysRole> findRolesByUserId (Integer userId);

    /**
     * 查询用户权限列表
     * @param userId
     * @return
     */
    Set<SysPermissions> findPermissionsByUserId (Integer userId);

    Page<SysUser> users (Pageable pageable);

    void saveUser (SysUser sysUser);

    void deleteUser (Integer id);

}


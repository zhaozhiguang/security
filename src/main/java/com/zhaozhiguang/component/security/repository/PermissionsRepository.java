package com.zhaozhiguang.component.security.repository;

import com.zhaozhiguang.component.security.entity.SysPermissions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 权限
 */
public interface PermissionsRepository extends PagingAndSortingRepository<SysPermissions, Integer> {

    @Query(nativeQuery = true, value = "select p.* from sys_role_permissions r left join sys_permissions p on r.permissions_id = p.id where r.role_id = ?1")
    List<SysPermissions> findAllByRoleId(Integer roleId);

    @Query(nativeQuery = true,value = "select p.* from sys_user_role u left join sys_role_permissions r on u.role_id = r.role_id " +
            "left join sys_permissions p on r.permissions_id = p.id where u.user_id = ?1")
    List<SysPermissions> findAllByUserId(Integer userId);
}

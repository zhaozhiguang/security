package com.zhaozhiguang.component.security.repository;

import com.zhaozhiguang.component.security.entity.SysRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 角色
 */
public interface RoleRepository extends PagingAndSortingRepository<SysRole, Integer> {

    @Query(nativeQuery = true, value = "select r.* from sys_user_role u left join sys_role r on u.role_id = r.id where u.user_id = ?1")
    List<SysRole> findAllByUserId(Integer userId);

}

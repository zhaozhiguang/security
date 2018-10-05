package com.zhaozhiguang.component.security.repository;

import com.zhaozhiguang.component.security.entity.SysUser;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 用户
 */
public interface UserRepository extends PagingAndSortingRepository<SysUser, Integer> {

    SysUser findByUserName(String userName);

}

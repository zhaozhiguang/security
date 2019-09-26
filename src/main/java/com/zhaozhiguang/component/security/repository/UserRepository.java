package com.zhaozhiguang.component.security.repository;

import com.zhaozhiguang.component.security.entity.SysUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 用户
 */
public interface UserRepository extends PagingAndSortingRepository<SysUser, Integer> {

    //@Query(nativeQuery = true, value = "select id, user_name, pass_word, avatar, create_time, update_time, status from sys_user where user_name = ?1")
    SysUser findByUserName(String userName);

}

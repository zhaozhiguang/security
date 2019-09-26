package com.zhaozhiguang.component.security.service;

import com.zhaozhiguang.component.security.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    Page<SysRole> roles (Pageable pageable);

    void saveRole (SysRole sysRole);

    void deleteRole (Integer id);

}

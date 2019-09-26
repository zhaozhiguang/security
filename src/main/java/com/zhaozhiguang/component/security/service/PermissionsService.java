package com.zhaozhiguang.component.security.service;

import com.zhaozhiguang.component.security.entity.SysPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionsService {

    Page<SysPermissions> permissions (Pageable pageable);

    void savePermissions (SysPermissions sysPermissions);

    void deletePermissions (Integer id);

}

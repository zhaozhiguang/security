package com.zhaozhiguang.component.security.service.impl;

import com.zhaozhiguang.component.security.entity.SysPermissions;
import com.zhaozhiguang.component.security.repository.PermissionsRepository;
import com.zhaozhiguang.component.security.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsRepository permissionsRepository;

    @Override
    public Page<SysPermissions> permissions(Pageable pageable) {
        return permissionsRepository.findAll(pageable);
    }

    @Override
    public void savePermissions(SysPermissions sysPermissions) {
        permissionsRepository.save(sysPermissions);
    }

    @Override
    public void deletePermissions(Integer id) {
        permissionsRepository.deleteById(id);
    }


}

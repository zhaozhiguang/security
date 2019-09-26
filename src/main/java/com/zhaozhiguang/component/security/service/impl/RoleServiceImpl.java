package com.zhaozhiguang.component.security.service.impl;

import com.zhaozhiguang.component.security.entity.SysRole;
import com.zhaozhiguang.component.security.repository.RoleRepository;
import com.zhaozhiguang.component.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<SysRole> roles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public void saveRole(SysRole sysRole) {
        roleRepository.save(sysRole);
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }

}

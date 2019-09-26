package com.zhaozhiguang.component.security.controller;


import com.zhaozhiguang.component.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    public RoleService roleService;

    @GetMapping("/list")
    public Object roles (@PageableDefault(page = 1) Pageable pageable) {
        return roleService.roles(pageable);
    }

}

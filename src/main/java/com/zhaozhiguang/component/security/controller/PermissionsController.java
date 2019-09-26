package com.zhaozhiguang.component.security.controller;

import com.zhaozhiguang.component.security.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;

    @GetMapping("/list")
    public Object permissions (@PageableDefault(page = 1)Pageable pageable) {
        return permissionsService.permissions(pageable);
    }

}

package com.zhaozhiguang.component.security.controller;

import com.zhaozhiguang.component.security.base.Result;
import com.zhaozhiguang.component.security.entity.SysUser;
import com.zhaozhiguang.component.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public Object getInfo (@AuthenticationPrincipal SysUser user){
        return Result.ok(user);
    }

    @GetMapping("/list")
    public Object users (@PageableDefault(page = 1) Pageable pageable) {
        return userService.users(pageable);
    }

}

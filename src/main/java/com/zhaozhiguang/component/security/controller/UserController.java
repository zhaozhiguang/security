package com.zhaozhiguang.component.security.controller;

import com.zhaozhiguang.component.security.base.Result;
import com.zhaozhiguang.component.security.entity.SysUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/info")
    public Object getInfo(@AuthenticationPrincipal SysUser user){
        return Result.ok(user);
    }

}

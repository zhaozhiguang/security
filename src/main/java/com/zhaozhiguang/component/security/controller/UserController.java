package com.zhaozhiguang.component.security.controller;

import com.zhaozhiguang.component.security.base.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/info")
    public Object getInfo(@AuthenticationPrincipal Authentication user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Result.ok(authentication.getPrincipal());
    }

}

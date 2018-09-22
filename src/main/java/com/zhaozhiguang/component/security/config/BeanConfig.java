package com.zhaozhiguang.component.security.config;

import com.zhaozhiguang.component.security.util.DateUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public DateUtil dateUtil(){
        return new DateUtil();
    }

}

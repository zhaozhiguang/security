package com.zhaozhiguang.component.security.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class CaptchaConfiguration {

    @ConfigurationProperties(prefix = "captcha")
    @Bean
    public Properties properties() {
        return new Properties();
    }

    @Bean
    public Config config() {
        return new Config(properties());
    }

    @Bean
    public Producer producer() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config());
        return defaultKaptcha;
    }
}

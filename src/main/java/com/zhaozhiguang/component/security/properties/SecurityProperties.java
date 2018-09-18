package com.zhaozhiguang.component.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "security.custom")
@Component
public class SecurityProperties {

    private boolean enableCaptcha = true;

    public boolean isEnableCaptcha() {
        return enableCaptcha;
    }

}

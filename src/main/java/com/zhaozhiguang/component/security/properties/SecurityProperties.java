package com.zhaozhiguang.component.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "security.custom")
@Component
public class SecurityProperties {

    private boolean enableCaptcha = true;

    private boolean enableCors = true;

    public boolean isEnableCaptcha() {
        return enableCaptcha;
    }

    public void setEnableCaptcha(boolean enableCaptcha) {
        this.enableCaptcha = enableCaptcha;
    }

    public boolean isEnableCors() {
        return enableCors;
    }

    public void setEnableCors(boolean enableCors) {
        this.enableCors = enableCors;
    }
}

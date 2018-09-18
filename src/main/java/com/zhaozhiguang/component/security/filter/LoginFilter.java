package com.zhaozhiguang.component.security.filter;

import com.zhaozhiguang.component.security.entity.JwtAuthenticationToken;
import com.zhaozhiguang.component.security.entity.User;
import com.zhaozhiguang.component.security.properties.SecurityConstants;
import com.zhaozhiguang.component.security.properties.SecurityProperties;
import com.zhaozhiguang.component.security.support.IncorrectCaptchaException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginFilter extends AbstractAuthenticationProcessingFilter implements InitializingBean {

    @Autowired
    private SecurityProperties securityProperties;

    public LoginFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public void afterPropertiesSet() {

    }

    /**
     * 创建token
     * @param request
     * @param response
     * @return
     */
    private JwtAuthenticationToken createToken(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        return new JwtAuthenticationToken(user, false);
    }

    /**
     * 验证码是否正确
     * @param request
     * @return
     */
    public static void verify(HttpServletRequest request) throws IncorrectCaptchaException {
        String expected = request.getParameter("captchaEncrypt");
        //获取用户页面输入的验证码
        String received = request.getParameter("captcha");
        if(received == null || expected  == null ||
                !expected.equalsIgnoreCase(DigestUtils.md5DigestAsHex((received + SecurityConstants.captchaEncrypt).getBytes()))){
            throw new IncorrectCaptchaException();
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(securityProperties.isEnableCaptcha()) verify(request);
        JwtAuthenticationToken token = createToken(request, response);
        return this.getAuthenticationManager().authenticate(token);
    }

}

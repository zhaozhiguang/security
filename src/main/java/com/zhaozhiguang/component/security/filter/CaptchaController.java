package com.zhaozhiguang.component.security.filter;

import com.google.code.kaptcha.Producer;
import com.zhaozhiguang.component.jwt.JWT;
import com.zhaozhiguang.component.jwt.algorithms.Algorithm;
import com.zhaozhiguang.component.security.base.Result;
import com.zhaozhiguang.component.security.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码请求接口
 * @author zhaozhiguang
 */
@RestController
public class CaptchaController {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private DateUtil dateUtil;

    @GetMapping("/captcha")
    public Object captcha() {
        String captcha = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(captcha);
        try (
            ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ){
            ImageIO.write(image, "png", baos);
            byte[] bytes = baos.toByteArray();//转换成字节
            Base64.Encoder encoder = Base64.getEncoder();
            String png_base64 =  "data:image/png;base64," + encoder.encode(bytes).toString().trim()
                    .replaceAll("\n", "").replaceAll("\r", "");//转换成base64串 删除 \r\n
            Map map = new HashMap();
            map.put("captcha", png_base64);
            String sercet = JWT.create().withExpiresAt(dateUtil.fromLocalDateTime(dateUtil.currentAddSeconds(60)))
                    .withSubject(captcha).sign(Algorithm.HMAC256("captcha"));
            map.put("captchaEncrypt", sercet);
            return Result.ok(map);
        } catch (IOException e) {
            logger.error("验证码转流失败", e);
            return Result.error();
        }

    }
}

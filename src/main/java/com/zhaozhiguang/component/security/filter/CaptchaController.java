package com.zhaozhiguang.component.security.filter;

import com.google.code.kaptcha.Producer;
import com.zhaozhiguang.component.security.base.Result;
import com.zhaozhiguang.component.security.properties.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    @GetMapping("/captcha")
    public Object captcha() {
        String captcha = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(captcha);
        try (
            ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ){
            ImageIO.write(image, "png", baos);
            byte[] bytes = baos.toByteArray();//转换成字节
            BASE64Encoder encoder = new BASE64Encoder();
            String png_base64 =  "data:image/png;base64," + encoder.encodeBuffer(bytes).trim()
                    .replaceAll("\n", "").replaceAll("\r", "");//转换成base64串 删除 \r\n
            Map map = new HashMap();
            map.put("captcha", png_base64);
            map.put("captchaEncrypt", DigestUtils.md5DigestAsHex((png_base64 + SecurityConstants.captchaEncrypt).getBytes()));
            return Result.ok(map);
        } catch (IOException e) {
            logger.error("验证码转流失败", e);
            return Result.error();
        }

    }
}

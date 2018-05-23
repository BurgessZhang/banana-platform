package com.burgess.banana.system.controller;

import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.system.entity.BananaSystemUserEntity;
import com.burgess.banana.system.form.BananaSystemLoginForm;
import com.burgess.banana.system.service.BananaSystemCaptchaService;
import com.burgess.banana.system.service.BananaSystemUserService;
import com.burgess.banana.system.service.BananaSystemUserTokenService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.controller
 * @file BananaSystemLoginController.java
 * @time 2018-05-23 21:57
 * @desc 登录相关Controller
 */
@RestController
public class BananaSystemLoginController extends BananaAbstractController {


    @Resource(name = "sysUserService")
    private BananaSystemUserService sysUserService;
    @Resource(name = "sysUserTokenService")
    private BananaSystemUserTokenService sysUserTokenService;
    @Resource(name = "sysCaptchaService")
    private BananaSystemCaptchaService sysCaptchaService;

    /**
     * 验证码
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @PostMapping("/sys/login")
    public Map<String, Object> login(@RequestBody BananaSystemLoginForm form) throws IOException {
        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
        if (!captcha) {
            return BananaResult.error("验证码不正确");
        }

        //用户信息
        BananaSystemUserEntity user = sysUserService.queryByUserName(form.getUsername());

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return BananaResult.error("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            return BananaResult.error("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        BananaResult r = sysUserTokenService.createToken(user.getUserId());
        return r;
    }


    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public BananaResult logout() {
        sysUserTokenService.logout(getUserId());
        return BananaResult.ok();
    }
}

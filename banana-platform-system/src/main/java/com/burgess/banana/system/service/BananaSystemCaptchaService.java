package com.burgess.banana.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.burgess.banana.system.entity.BananaSystemCaptchaEntity;

import java.awt.image.BufferedImage;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service
 * @file BananaSystemCaptchaService.java
 * @time 2018-05-23 21:19
 * @desc 验证码
 */
public interface BananaSystemCaptchaService extends IService<BananaSystemCaptchaEntity> {

    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     *
     * @param uuid uuid
     * @param code 验证码
     * @return true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}

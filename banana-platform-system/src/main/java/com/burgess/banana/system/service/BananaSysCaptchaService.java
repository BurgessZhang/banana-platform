package com.burgess.banana.system.service;

import com.baomidou.mybatisplus.service.IService;

import java.awt.image.BufferedImage;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service
 * @file_name BananaSysCaptchaService.java
 * @description 验证码
 * @create 2018-04-19 13:13
 */
public interface BananaSysCaptchaService{

    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}

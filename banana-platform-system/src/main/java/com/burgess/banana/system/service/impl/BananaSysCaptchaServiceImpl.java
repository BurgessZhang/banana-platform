package com.burgess.banana.system.service.impl;

import com.burgess.banana.common.exception.BananaResultException;
import com.burgess.banana.common.util.BananaDateUtils;
import com.burgess.banana.system.entity.BananaSysCaptcha;
import com.burgess.banana.system.mapper.BananaSysCaptchaMapper;
import com.burgess.banana.system.service.BananaSysCaptchaService;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service.impl
 * @file_name BananaSysCaptchaServiceImpl.java
 * @description 验证码service接口实现
 * @create 2018-04-19 13:14
 */
@Service("bananaSysCaptchaService")
public class BananaSysCaptchaServiceImpl implements BananaSysCaptchaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BananaSysCaptchaServiceImpl.class);

    @Autowired
    private Producer producer;

    @Autowired
    private BananaSysCaptchaMapper bananaSysCaptchaMapper;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)){
            throw new BananaResultException("uuid不能为空!");
        }
        //生成文件验证码
        String code = producer.createText();

        BananaSysCaptcha captcha = new BananaSysCaptcha();
        captcha.setUuid(uuid);
        captcha.setCode(code);
        //5分钟后过期
        captcha.setExpireTime(BananaDateUtils.addDateMinutes(new Date(),5));

        bananaSysCaptchaMapper.insert(captcha);

        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        BananaSysCaptcha captcha = bananaSysCaptchaMapper.selectByPrimaryKey(uuid);
        if (null == captcha){
            return false;
        }
        //删除验证码
        bananaSysCaptchaMapper.deleteByPrimaryKey(uuid);

        if (captcha.getCode().equalsIgnoreCase(code) && captcha.getExpireTime().getTime() >= System.currentTimeMillis()){
            return true;
        }
        return false;
    }
}

package com.burgess.banana.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.burgess.banana.common.exception.BananaResultException;
import com.burgess.banana.common.util.BananaDateUtils;
import com.burgess.banana.system.entity.BananaSystemCaptchaEntity;
import com.burgess.banana.system.mapper.BananaSystemCaptchaMapper;
import com.burgess.banana.system.service.BananaSystemCaptchaService;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service.impl
 * @file BananaSystemCaptchaServiceImpl.java
 * @time 2018-05-23 21:30
 * @desc 验证码service接口实现
 */
@Service("sysCaptchaService")
public class BananaSystemCaptchaServiceImpl extends ServiceImpl<BananaSystemCaptchaMapper, BananaSystemCaptchaEntity> implements BananaSystemCaptchaService {


    @Autowired
    private Producer producer;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) {
            throw new BananaResultException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();

        BananaSystemCaptchaEntity captchaEntity = new BananaSystemCaptchaEntity();
        captchaEntity.setUuid(uuid);
        captchaEntity.setCode(code);
        //5分钟后过期
        captchaEntity.setExpireTime(BananaDateUtils.addDateMinutes(new Date(), 5));
        this.insert(captchaEntity);

        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        BananaSystemCaptchaEntity captchaEntity = this.selectOne(new EntityWrapper<BananaSystemCaptchaEntity>().eq("uuid", uuid));
        if (captchaEntity == null) {
            return false;
        }

        //删除验证码
        this.deleteById(uuid);

        if (captchaEntity.getCode().equalsIgnoreCase(code) && captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis()) {
            return true;
        }

        return false;
    }

}

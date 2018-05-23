package com.burgess.banana.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.system.entity.BananaSystemUserTokenEntity;
import com.burgess.banana.system.mapper.BananaSystemUserTokenMapper;
import com.burgess.banana.system.oauth2.BananaTokenGenerator;
import com.burgess.banana.system.service.BananaSystemUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service.impl
 * @file BananaSystemUserTokenServiceImpl.java
 * @time 2018-05-23 21:51
 * @desc 系统用户tokenservice接口实现
 */
@Service("sysUserTokenService")
public class BananaSystemUserTokenServiceImpl extends ServiceImpl<BananaSystemUserTokenMapper, BananaSystemUserTokenEntity> implements BananaSystemUserTokenService {

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;


    @Override
    public BananaResult createToken(long userId) {
        //生成一个token
        String token = BananaTokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        BananaSystemUserTokenEntity tokenEntity = this.selectById(userId);
        if (tokenEntity == null) {
            tokenEntity = new BananaSystemUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            this.insert(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }

        BananaResult r = BananaResult.ok().put("token", token).put("expire", EXPIRE);

        return r;
    }

    @Override
    public void logout(long userId) {
        //生成一个token
        String token = BananaTokenGenerator.generateValue();

        //修改token
        BananaSystemUserTokenEntity tokenEntity = new BananaSystemUserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }
}

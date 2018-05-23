package com.burgess.banana.system.redis;

import com.burgess.banana.common.util.BananaRedisKeys;
import com.burgess.banana.common.util.BananaRedisUtils;
import com.burgess.banana.system.entity.BananaSystemConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统配置Redis
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017/7/18 21:08
 */
@Component
public class SysConfigRedis {
    @Autowired
    private BananaRedisUtils redisUtils;

    public void saveOrUpdate(BananaSystemConfigEntity config) {
        if(config == null){
            return ;
        }
        String key = BananaRedisKeys.getSysConfigKey(config.getKey());
        redisUtils.set(key, config);
    }

    public void delete(String configKey) {
        String key = BananaRedisKeys.getSysConfigKey(configKey);
        redisUtils.delete(key);
    }

    public BananaSystemConfigEntity get(String configKey){
        String key = BananaRedisKeys.getSysConfigKey(configKey);
        return redisUtils.get(key, BananaSystemConfigEntity.class);
    }
}

package com.burgess.banana.system.redis;

import com.burgess.banana.common.util.BananaRedisKeys;
import com.burgess.banana.common.util.BananaRedisUtils;
import com.burgess.banana.system.entity.BananaSystemConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.redis
 * @file BananaSystemConfigRedis.java
 * @time 2018-05-23 22:21
 * @desc 系统配置redis
 */
@Component("sysConfigRedis")
public class BananaSystemConfigRedis {

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

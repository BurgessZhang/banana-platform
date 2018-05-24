package com.burgess.banana.config.redis;

import com.burgess.banana.config.entity.BananaSystemConfigEntity;
import com.burgess.banana.redis.util.BananaRedisKeys;
import com.burgess.banana.redis.util.BananaRedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.redis.config
 * @file BananaSystemConfigRedis.java
 * @time 2018-05-24 11:21
 * @desc 系统配置redis
 */
@Component("sysConfigRedis")
public class BananaSystemConfigRedis {

    @Resource(name = "redisUtils")
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

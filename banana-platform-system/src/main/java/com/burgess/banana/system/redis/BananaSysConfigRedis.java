package com.burgess.banana.system.redis;

import com.burgess.banana.common.util.BananaRedisKeys;
import com.burgess.banana.common.util.BananaRedisUtils;
import com.burgess.banana.system.entity.BananaSysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.redis
 * @file_name BananaSysConfigRedis.java
 * @description 系统配置redis
 * @create 2018-04-19 11:07
 */
@Component
public class BananaSysConfigRedis {

    @Autowired
    private BananaRedisUtils redisUtils;

    public void saveOrUpdate(BananaSysConfig config) {
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

    public BananaSysConfig get(String configKey){
        String key = BananaRedisKeys.getSysConfigKey(configKey);
        return redisUtils.get(key, BananaSysConfig.class);
    }

}

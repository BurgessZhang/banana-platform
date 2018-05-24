package com.burgess.banana.redis.util;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.redis.util
 * @file BananaRedisKeys.java
 * @time 2018-05-24 11:24
 * @desc redis所有的key
 */
public class BananaRedisKeys {

    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }
}

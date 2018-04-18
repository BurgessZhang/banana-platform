package com.burgess.banana.common.utils;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.utils
 * @file_name BananaRedisKeys.java
 * @description redis所有的key
 * @create 2018-04-18 22:31
 */
public class BananaRedisKeys {

    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }

}

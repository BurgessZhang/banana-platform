package com.burgess.banana.system.service;

import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.system.entity.BananaSysConfig;

import java.util.Map;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service
 * @file_name BananaSysConfigService.java
 * @description 系统配置信息
 * @create 2018-04-19 13:16
 */
public interface BananaSysConfigService {

    BananaPageUtils queryPage(Map<String, Object> params);

    /**
     * 保存配置信息
     */
    void save(BananaSysConfig config);

    /**
     * 更新配置信息
     */
    void update(BananaSysConfig config);

    /**
     * 根据key，更新value
     */
   void updateValueByKey(String key, String value);

    /**
     * 删除配置信息
     */
    void deleteBatch(Long[] ids);

    /**
     * 根据key，获取配置的value值
     *
     * @param key           key
     */
    String getValue(String key);

    /**
     * 根据key，获取value的Object对象
     * @param key    key
     * @param clazz  Object对象
     */
    <T> T getConfigObject(String key, Class<T> clazz);

}

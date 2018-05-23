package com.burgess.banana.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.system.entity.BananaSystemConfigEntity;

import java.util.Map;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service
 * @file BananaSystemConfigService.java
 * @time 2018-05-23 21:20
 * @desc 系统配置信息
 */
public interface BananaSystemConfigService extends IService<BananaSystemConfigEntity> {

    BananaPageUtils queryPage(Map<String, Object> params);

    /**
     * 保存配置信息
     */
    void save(BananaSystemConfigEntity config);

    /**
     * 更新配置信息
     */
    void update(BananaSystemConfigEntity config);

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
     * @param key key
     */
    String getValue(String key);

    /**
     * 根据key，获取value的Object对象
     *
     * @param key   key
     * @param clazz Object对象
     */
    <T> T getConfigObject(String key, Class<T> clazz);
}

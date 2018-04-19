package com.burgess.banana.system.service.impl;

import com.burgess.banana.common.exception.BananaResultException;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.system.entity.BananaSysConfig;
import com.burgess.banana.system.mapper.BananaSysConfigMapper;
import com.burgess.banana.system.redis.BananaSysConfigRedis;
import com.burgess.banana.system.service.BananaSysConfigService;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service.impl
 * @file_name BananaSysConfigServiceImpl.java
 * @description
 * @create 2018-04-19 14:11
 */
@Service("bananaSysConfigService")
public class BananaSysConfigServiceImpl implements BananaSysConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BananaSysConfigServiceImpl.class);

    @Autowired
    private BananaSysConfigMapper bananaSysConfigMapper;

    @Autowired
    private BananaSysConfigRedis bananaSysConfigRedis;

    @Override
    public BananaPageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<BananaSysConfig> page = null;
        return new BananaPageUtils(page);
    }

    @Override
    public void save(BananaSysConfig config) {
        bananaSysConfigMapper.insert(config);
        bananaSysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BananaSysConfig config) {
        bananaSysConfigMapper.updateByPrimaryKey(config);
        bananaSysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateValueByKey(String key, String value) {
        bananaSysConfigMapper.updateValueByKey(key,value);
        bananaSysConfigRedis.delete(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        for(Long id : ids){
            BananaSysConfig config = bananaSysConfigMapper.selectByPrimaryKey(id);
            bananaSysConfigRedis.delete(config.getKey());
            bananaSysConfigMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public String getValue(String key) {
        BananaSysConfig config = bananaSysConfigRedis.get(key);
        if (null == config){
            config = bananaSysConfigMapper.queryByKey(key);
            bananaSysConfigRedis.saveOrUpdate(config);
        }
        return null == config ? null : config.getValue();
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz){
        String value = getValue(key);
        if (StringUtils.isNotBlank(value)){
            return new Gson().fromJson(value,clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
           throw new BananaResultException("获取参数失败!");
        }
    }
}

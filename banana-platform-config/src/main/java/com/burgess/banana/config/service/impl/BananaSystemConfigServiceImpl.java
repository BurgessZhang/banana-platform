package com.burgess.banana.config.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.burgess.banana.common.exception.BananaResultException;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaQuery;
import com.burgess.banana.config.entity.BananaSystemConfigEntity;
import com.burgess.banana.config.mapper.BananaSystemConfigMapper;
import com.burgess.banana.config.redis.BananaSystemConfigRedis;
import com.burgess.banana.config.service.BananaSystemConfigService;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service.impl
 * @file BananaSystemConfigServiceImpl.java
 * @time 2018-05-23 21:35
 * @desc 系统配置service接口实现
 */
@Service("sysConfigService")
public class BananaSystemConfigServiceImpl extends ServiceImpl<BananaSystemConfigMapper, BananaSystemConfigEntity> implements BananaSystemConfigService {


    @Resource(name = "sysConfigRedis")
    private BananaSystemConfigRedis sysConfigRedis;

    @Override
    public BananaPageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");

        Page<BananaSystemConfigEntity> page = this.selectPage(
                new BananaQuery<BananaSystemConfigEntity>(params).getPage(),
                new EntityWrapper<BananaSystemConfigEntity>()
                        .like(StringUtils.isNotBlank(key), "key", key)
                        .eq("status", 1)
        );

        return new BananaPageUtils(page);
    }

    @Override
    public void save(BananaSystemConfigEntity config) {
        this.insert(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BananaSystemConfigEntity config) {
        this.updateById(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateValueByKey(String key, String value) {
        baseMapper.updateValueByKey(key, value);
        sysConfigRedis.delete(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        for (Long id : ids) {
            BananaSystemConfigEntity config = this.selectById(id);
            sysConfigRedis.delete(config.getKey());
        }

        this.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public String getValue(String key) {
        BananaSystemConfigEntity config = sysConfigRedis.get(key);
        if (config == null) {
            config = baseMapper.queryByKey(key);
            sysConfigRedis.saveOrUpdate(config);
        }

        return config == null ? null : config.getValue();
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if (StringUtils.isNotBlank(value)) {
            return new Gson().fromJson(value, clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BananaResultException("获取参数失败");
        }
    }

}

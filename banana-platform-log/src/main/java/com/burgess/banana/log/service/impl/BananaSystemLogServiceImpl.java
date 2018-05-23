package com.burgess.banana.log.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaQuery;
import com.burgess.banana.log.entity.BananaSystemLogEntity;
import com.burgess.banana.log.mapper.BananaSystemLogMapper;
import com.burgess.banana.log.service.BananaSystemLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.log.service.impl
 * @file BananaSystemLogServiceImpl.java
 * @time 2018-05-23 16:24
 * @desc 系统日志service接口实现
 */
@Service("sysLogService")
public class BananaSystemLogServiceImpl extends ServiceImpl<BananaSystemLogMapper, BananaSystemLogEntity> implements BananaSystemLogService {

    @Override
    public BananaPageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");

        Page<BananaSystemLogEntity> page = this.selectPage(
                new BananaQuery<BananaSystemLogEntity>(params).getPage(),
                new EntityWrapper<BananaSystemLogEntity>().like(StringUtils.isNotBlank(key), "username", key)
        );

        return new BananaPageUtils(page);
    }
}

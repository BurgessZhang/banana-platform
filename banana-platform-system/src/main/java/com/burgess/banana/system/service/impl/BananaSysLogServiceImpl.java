package com.burgess.banana.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.system.entity.BananaSysLog;
import com.burgess.banana.system.mapper.BananaSysLogMapper;
import com.burgess.banana.system.service.BananaSysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service.impl
 * @file_name BananaSysLogServiceImpl.java
 * @description
 * @create 2018-04-19 14:10
 */
@Service("bananaSysLogService")
public class BananaSysLogServiceImpl implements BananaSysLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BananaSysLogServiceImpl.class);

    @Autowired
    private BananaSysLogMapper bananaSysLogMapper;


    @Override
    public BananaPageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        Page<BananaSysLog> page = null;
        return new BananaPageUtils(page);
    }
}

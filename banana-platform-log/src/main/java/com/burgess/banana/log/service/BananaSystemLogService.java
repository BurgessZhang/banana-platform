package com.burgess.banana.log.service;

import com.baomidou.mybatisplus.service.IService;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.log.entity.BananaSystemLogEntity;

import java.util.Map;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.log.service
 * @file BananaSystemLogService.java
 * @time 2018-05-23 16:22
 * @desc 系统日志service接口
 */
public interface BananaSystemLogService extends IService<BananaSystemLogEntity> {

    BananaPageUtils queryPage(Map<String, Object> params);
}

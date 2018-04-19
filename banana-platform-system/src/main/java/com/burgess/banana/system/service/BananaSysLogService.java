package com.burgess.banana.system.service;

import com.burgess.banana.common.util.BananaPageUtils;

import java.util.Map;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service
 * @file_name BananaSysLogService.java
 * @description 系统日志
 * @create 2018-04-19 13:18
 */
public interface BananaSysLogService {

    BananaPageUtils queryPage(Map<String, Object> params);

}

package com.burgess.banana.schedule.service;

import com.baomidou.mybatisplus.service.IService;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.schedule.entity.BananaScheduleJobEntity;

import java.util.Map;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.schedule.service
 * @file BananaScheduleJobService.java
 * @time 2018-05-25 15:26
 * @desc 定时任务service接口
 */
public interface BananaScheduleJobService extends IService<BananaScheduleJobEntity> {

    BananaPageUtils queryPage(Map<String, Object> params);
}

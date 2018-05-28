package com.burgess.banana.schedule.service;

import com.baomidou.mybatisplus.service.IService;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.schedule.entity.BananaScheduleJobLogEntity;

import java.util.Map;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.schedule.service
 * @file BananaScheduleJobLogService.java
 * @time 2018-05-25 15:27
 * @desc 定时任务日志service接口
 */
public interface BananaScheduleJobLogService extends IService<BananaScheduleJobLogEntity> {


    /**获取定时任务日志列表*/
    BananaPageUtils queryPage(Map<String, Object> params);
}

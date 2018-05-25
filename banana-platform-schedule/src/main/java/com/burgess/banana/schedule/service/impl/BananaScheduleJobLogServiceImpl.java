package com.burgess.banana.schedule.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaQuery;
import com.burgess.banana.schedule.entity.BananaScheduleJobLogEntity;
import com.burgess.banana.schedule.mapper.BananaScheduleJobLogMapper;
import com.burgess.banana.schedule.service.BananaScheduleJobLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.schedule.service.impl
 * @file BananaScheduleJobLogServiceImpl.java
 * @time 2018-05-25 15:28
 * @desc 定时任务日志service接口实现
 */
@Service("scheduleJobLogService")
public class BananaScheduleJobLogServiceImpl extends ServiceImpl<BananaScheduleJobLogMapper,BananaScheduleJobLogEntity> implements BananaScheduleJobLogService {


    @Override
    public BananaPageUtils queryPage(Map<String, Object> params) {
        String jobId = (String)params.get("jobId");

        Page<BananaScheduleJobLogEntity> page = this.selectPage(
                new BananaQuery<BananaScheduleJobLogEntity>(params).getPage(),
                new EntityWrapper<BananaScheduleJobLogEntity>().like(StringUtils.isNotBlank(jobId),"job_id", jobId)
        );

        return new BananaPageUtils(page);
    }
}

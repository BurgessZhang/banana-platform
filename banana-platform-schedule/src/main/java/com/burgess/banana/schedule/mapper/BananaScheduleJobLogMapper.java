package com.burgess.banana.schedule.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.burgess.banana.schedule.entity.BananaScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.schedule.mapper
 * @file BananaScheduleJobLogMapper.java
 * @time 2018-05-25 15:19
 * @desc 定时任务日志Mapper
 */
@Mapper
public interface BananaScheduleJobLogMapper extends BaseMapper<BananaScheduleJobLogEntity> {


}

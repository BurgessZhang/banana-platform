package com.burgess.banana.schedule.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.burgess.banana.schedule.entity.BananaScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.schedule.mapper
 * @file BananaScheduleJobMapper.java
 * @time 2018-05-25 15:19
 * @desc 定时任务Mapper
 */
@Mapper
public interface BananaScheduleJobMapper extends BaseMapper<BananaScheduleJobEntity> {


    /**批量更新状态*/
    int updateBatch(Map<String, Object> map);
}

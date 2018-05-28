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

    /**
     * 保存定时任务
     */
    void save(BananaScheduleJobEntity scheduleJob);

    /**
     * 更新定时任务
     */
    void update(BananaScheduleJobEntity scheduleJob);

    /**
     * 批量删除定时任务
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 立即执行
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     */
    void pause(Long[] jobIds);

    /**
     * 恢复运行
     */
    void resume(Long[] jobIds);
}

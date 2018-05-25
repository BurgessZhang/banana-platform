package com.burgess.banana.schedule.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.burgess.banana.common.util.BananaConstant;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaQuery;
import com.burgess.banana.schedule.entity.BananaScheduleJobEntity;
import com.burgess.banana.schedule.mapper.BananaScheduleJobMapper;
import com.burgess.banana.schedule.service.BananaScheduleJobService;
import com.burgess.banana.schedule.util.BananaScheduleUtils;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.schedule.service
 * @file BananaScheduleJobService.java
 * @time 2018-05-25 15:24
 * @desc 定时任务service接口
 */
@Service("scheduleJobService")
public class BananaScheduleJobServiceImpl extends ServiceImpl<BananaScheduleJobMapper,BananaScheduleJobEntity> implements BananaScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    /**
     * @class_name BananaScheduleJobServiceImpl
     * @method init
     * @desc 项目启动时，初始化定时器
     * @author free.zhang
     * @date 2018/5/25 15:35
     * @param '[]
     * @return void
     */
    @PostConstruct
    public void init(){

        List<BananaScheduleJobEntity> scheduleJobList = this.selectList(null);
        for(BananaScheduleJobEntity scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = BananaScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                BananaScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                BananaScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public BananaPageUtils queryPage(Map<String, Object> params) {
        String beanName = (String)params.get("beanName");

        Page<BananaScheduleJobEntity> page = this.selectPage(
                new BananaQuery<BananaScheduleJobEntity>(params).getPage(),
                new EntityWrapper<BananaScheduleJobEntity>().like(StringUtils.isNotBlank(beanName),"bean_name", beanName)
        );

        return new BananaPageUtils(page);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BananaScheduleJobEntity scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(BananaConstant.ScheduleStatus.NORMAL.getValue());
        this.insert(scheduleJob);

        BananaScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BananaScheduleJobEntity scheduleJob) {
        BananaScheduleUtils.updateScheduleJob(scheduler, scheduleJob);

        this.updateById(scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
        for(Long jobId : jobIds){
            BananaScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }

        //删除数据
        this.deleteBatchIds(Arrays.asList(jobIds));
    }

    @Override
    public int updateBatch(Long[] jobIds, int status){
        Map<String, Object> map = new HashMap<>();
        map.put("list", jobIds);
        map.put("status", status);
        return baseMapper.updateBatch(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for(Long jobId : jobIds){
            BananaScheduleUtils.run(scheduler, this.selectById(jobId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
            BananaScheduleUtils.pauseJob(scheduler, jobId);
        }

        updateBatch(jobIds, BananaConstant.ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        for(Long jobId : jobIds){
            BananaScheduleUtils.resumeJob(scheduler, jobId);
        }

        updateBatch(jobIds, BananaConstant.ScheduleStatus.NORMAL.getValue());
    }

}

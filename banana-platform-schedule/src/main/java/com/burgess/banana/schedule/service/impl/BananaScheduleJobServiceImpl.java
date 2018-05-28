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
     * @file BananaScheduleJobServiceImpl.java
     * @method init
     * @desc 项目启动时，初始化定时器
     * @author free.zhang
     * @date 2018/5/28 11:15
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

    /**
     * @file BananaScheduleJobServiceImpl.java
     * @method queryPage
     * @desc 定时任务列表
     * @author free.zhang
     * @date 2018/5/28 11:16
     * @param '[params]
     * @return com.burgess.banana.common.util.BananaPageUtils
     */
    @Override
    public BananaPageUtils queryPage(Map<String, Object> params) {
        String beanName = (String)params.get("beanName");
        Page<BananaScheduleJobEntity> page = this.selectPage(
                new BananaQuery<BananaScheduleJobEntity>(params).getPage(),
                new EntityWrapper<BananaScheduleJobEntity>().like(StringUtils.isNotBlank(beanName),"bean_name", beanName)
        );

        return new BananaPageUtils(page);
    }


    /**
     * @file BananaScheduleJobServiceImpl.java
     * @method save
     * @desc 添加定时任务
     * @author free.zhang
     * @date 2018/5/28 11:16
     * @param '[scheduleJob]
     * @return void
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BananaScheduleJobEntity scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(BananaConstant.ScheduleStatus.NORMAL.getValue());
        this.insert(scheduleJob);

        BananaScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    /**
     * @file BananaScheduleJobServiceImpl.java
     * @method update
     * @desc 更新定时任务
     * @author free.zhang
     * @date 2018/5/28 11:19
     * @param '[scheduleJob]
     * @return void
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BananaScheduleJobEntity scheduleJob) {

        BananaScheduleUtils.updateScheduleJob(scheduler, scheduleJob);

        this.updateById(scheduleJob);
    }

    /**
     * @file BananaScheduleJobServiceImpl.java
     * @method deleteBatch
     * @desc 批量删除定时任务
     * @author free.zhang
     * @date 2018/5/28 11:19
     * @param '[jobIds]
     * @return void
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {

        for(Long jobId : jobIds){
            BananaScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        //删除数据
        this.deleteBatchIds(Arrays.asList(jobIds));
    }

    /**
     * @file BananaScheduleJobServiceImpl.java
     * @method updateBatch
     * @desc 批量更新定时任务
     * @author free.zhang
     * @date 2018/5/28 11:19
     * @param '[jobIds, status]
     * @return int
     */
    @Override
    public int updateBatch(Long[] jobIds, int status){
        Map<String, Object> map = new HashMap<>();
        map.put("list", jobIds);
        map.put("status", status);
        return baseMapper.updateBatch(map);
    }

    /**
     * @file BananaScheduleJobServiceImpl.java
     * @method run
     * @desc 立即执行定时任务
     * @author free.zhang
     * @date 2018/5/28 11:20
     * @param '[jobIds]
     * @return void
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for(Long jobId : jobIds){
            BananaScheduleUtils.run(scheduler, this.selectById(jobId));
        }
    }

    /**
     * @file BananaScheduleJobServiceImpl.java
     * @method pause
     * @desc 暂停运行
     * @author free.zhang
     * @date 2018/5/28 11:20
     * @param '[jobIds]
     * @return void
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
            BananaScheduleUtils.pauseJob(scheduler, jobId);
        }

        updateBatch(jobIds, BananaConstant.ScheduleStatus.PAUSE.getValue());
    }

    /**
     * @file BananaScheduleJobServiceImpl.java
     * @method resume
     * @desc 恢复运行
     * @author free.zhang
     * @date 2018/5/28 11:21
     * @param '[jobIds]
     * @return void
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        for(Long jobId : jobIds){
            BananaScheduleUtils.resumeJob(scheduler, jobId);
        }

        updateBatch(jobIds, BananaConstant.ScheduleStatus.NORMAL.getValue());
    }

}

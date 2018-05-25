package com.burgess.banana.schedule.util;

import com.burgess.banana.common.util.BananaSpringContextUtils;
import com.burgess.banana.schedule.entity.BananaScheduleJobEntity;
import com.burgess.banana.schedule.entity.BananaScheduleJobLogEntity;
import com.burgess.banana.schedule.service.BananaScheduleJobLogService;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.schedule.util
 * @file ScheduleJob.java
 * @time 2018-05-25 15:50
 * @desc 定时任务
 */
public class BananaScheduleJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        BananaScheduleJobEntity scheduleJob = (BananaScheduleJobEntity) context.getMergedJobDataMap()
                .get(BananaScheduleJobEntity.JOB_PARAM_KEY);

        //获取spring bean
        BananaScheduleJobLogService scheduleJobLogService = (BananaScheduleJobLogService) BananaSpringContextUtils.getBean("scheduleJobLogService");

        //数据库保存执行记录
        BananaScheduleJobLogEntity log = new BananaScheduleJobLogEntity();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            BananaScheduleRunnable task = new BananaScheduleRunnable(scheduleJob.getBeanName(),
                    scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);

            future.get();

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int)times);
            //任务状态    0：成功    1：失败
            log.setStatus(0);

            logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes((int)times);

            //任务状态    0：成功    1：失败
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
        }finally {
            scheduleJobLogService.insert(log);
        }
    }
}

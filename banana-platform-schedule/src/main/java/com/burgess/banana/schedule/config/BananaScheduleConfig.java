package com.burgess.banana.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.schedule.config
 * @file BananaScheduleConfig.java
 * @time 2018-05-25 15:05
 * @desc 定时任务配置
 */
@Configuration
public class BananaScheduleConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource){
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setDataSource(dataSource);

        //quartz参数
        Properties properties = new Properties();
        properties.put("org.quartz.scheduler.instanceName", "RenrenScheduler");
        properties.put("org.quartz.scheduler.instanceId", "AUTO");

        //线程池配置
        properties.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        properties.put("org.quartz.threadPool.threadCount", "20");
        properties.put("org.quartz.threadPool.threadPriority", "5");

        //JobStore配置
        properties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");

        //集群配置
        properties.put("org.quartz.jobStore.isClustered", "true");
        properties.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        properties.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");

        properties.put("org.quartz.jobStore.misfireThreshold", "12000");
        properties.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        properties.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");
        factoryBean.setQuartzProperties(properties);

        factoryBean.setSchedulerName("RenrenScheduler");

        //延时启动
        factoryBean.setStartupDelay(30);
        factoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        //可选，QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factoryBean.setOverwriteExistingJobs(true);
        //设置自动启动，默认为true
        factoryBean.setAutoStartup(true);

        return factoryBean;
    }

}

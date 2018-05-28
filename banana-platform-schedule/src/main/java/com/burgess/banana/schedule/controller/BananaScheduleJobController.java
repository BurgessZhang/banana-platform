package com.burgess.banana.schedule.controller;

import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.common.validator.BananaValidatorUtils;
import com.burgess.banana.log.annotation.BananaSystemLog;
import com.burgess.banana.schedule.entity.BananaScheduleJobEntity;
import com.burgess.banana.schedule.service.BananaScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.schedule.controller
 * @file BananaScheduleJobController.java
 * @time 2018-05-25 15:38
 * @desc 定时任务
 */
@RestController
@RequestMapping("/sys/schedule")
public class BananaScheduleJobController {


    @Resource(name = "scheduleJobService")
    private BananaScheduleJobService scheduleJobService;

    /**
     * @param '[params]
     * @return com.burgess.banana.common.util.BananaResult
     * @file BananaScheduleJobController.java
     * @method list
     * @desc 定时任务列表
     * @author free.zhang
     * @date 2018/5/28 11:21
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:schedule:list")
    public BananaResult list(@RequestParam Map<String, Object> params) {

        BananaPageUtils page = scheduleJobService.queryPage(params);

        return BananaResult.ok().put("page", page);
    }

    /**
     * @param '[jobId]
     * @return com.burgess.banana.common.util.BananaResult
     * @file BananaScheduleJobController.java
     * @method info
     * @desc 定时任务信息
     * @author free.zhang
     * @date 2018/5/28 11:22
     */
    @GetMapping("/info/{jobId}")
    @RequiresPermissions("sys:schedule:info")
    public BananaResult info(@PathVariable("jobId") Long jobId) {

        BananaScheduleJobEntity schedule = scheduleJobService.selectById(jobId);

        return BananaResult.ok().put("schedule", schedule);
    }

    /**
     * @param '[scheduleJob]
     * @return com.burgess.banana.common.util.BananaResult
     * @file BananaScheduleJobController.java
     * @method save
     * @desc 保存定时任务
     * @author free.zhang
     * @date 2018/5/28 11:22
     */
    @BananaSystemLog("保存定时任务")
    @PostMapping("/save")
    @RequiresPermissions("sys:schedule:save")
    public BananaResult save(@RequestBody BananaScheduleJobEntity scheduleJob) {

        BananaValidatorUtils.validateEntity(scheduleJob);

        scheduleJobService.save(scheduleJob);

        return BananaResult.ok();
    }

    /**
     * @param '[scheduleJob]
     * @return com.burgess.banana.common.util.BananaResult
     * @file BananaScheduleJobController.java
     * @method update
     * @desc 修改定时任务
     * @author free.zhang
     * @date 2018/5/28 11:22
     */
    @BananaSystemLog("修改定时任务")
    @PostMapping("/update")
    @RequiresPermissions("sys:schedule:update")
    public BananaResult update(@RequestBody BananaScheduleJobEntity scheduleJob) {

        BananaValidatorUtils.validateEntity(scheduleJob);

        scheduleJobService.update(scheduleJob);

        return BananaResult.ok();
    }

    /**
     * @param '[jobIds]
     * @return com.burgess.banana.common.util.BananaResult
     * @file BananaScheduleJobController.java
     * @method delete
     * @desc 删除定时任务
     * @author free.zhang
     * @date 2018/5/28 11:23
     */
    @BananaSystemLog("删除定时任务")
    @PostMapping("/delete")
    @RequiresPermissions("sys:schedule:delete")
    public BananaResult delete(@RequestBody Long[] jobIds) {

        scheduleJobService.deleteBatch(jobIds);

        return BananaResult.ok();
    }

    /**
     * @param '[jobIds]
     * @return com.burgess.banana.common.util.BananaResult
     * @file BananaScheduleJobController.java
     * @method run
     * @desc 立即执行任务
     * @author free.zhang
     * @date 2018/5/28 11:23
     */
    @BananaSystemLog("立即执行任务")
    @PostMapping("/run")
    @RequiresPermissions("sys:schedule:run")
    public BananaResult run(@RequestBody Long[] jobIds) {

        scheduleJobService.run(jobIds);

        return BananaResult.ok();
    }

    /**
     * @param '[jobIds]
     * @return com.burgess.banana.common.util.BananaResult
     * @file BananaScheduleJobController.java
     * @method pause
     * @desc 暂停定时任务
     * @author free.zhang
     * @date 2018/5/28 11:23
     */
    @BananaSystemLog("暂停定时任务")
    @PostMapping("/pause")
    @RequiresPermissions("sys:schedule:pause")
    public BananaResult pause(@RequestBody Long[] jobIds) {

        scheduleJobService.pause(jobIds);

        return BananaResult.ok();
    }

    /**
     * @param '[jobIds]
     * @return com.burgess.banana.common.util.BananaResult
     * @file BananaScheduleJobController.java
     * @method resume
     * @desc 恢复定时任务
     * @author free.zhang
     * @date 2018/5/28 11:24
     */
    @BananaSystemLog("恢复定时任务")
    @PostMapping("/resume")
    @RequiresPermissions("sys:schedule:resume")
    public BananaResult resume(@RequestBody Long[] jobIds) {

        scheduleJobService.resume(jobIds);

        return BananaResult.ok();
    }
}

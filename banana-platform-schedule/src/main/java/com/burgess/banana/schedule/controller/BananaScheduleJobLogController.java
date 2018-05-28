package com.burgess.banana.schedule.controller;

import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.schedule.entity.BananaScheduleJobLogEntity;
import com.burgess.banana.schedule.service.BananaScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.schedule.controller
 * @file BananaScheduleJobLogController.java
 * @time 2018-05-25 15:36
 * @desc 定时任务日志
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class BananaScheduleJobLogController {

    @Resource(name = "scheduleJobLogService")
    private BananaScheduleJobLogService scheduleJobLogService;

    /**
     * @param '[params]
     * @return com.burgess.banana.common.util.BananaResult
     * @class_name BananaScheduleJobLogController
     * @method list
     * @desc 定时任务日志列表
     * @author free.zhang
     * @date 2018/5/25 15:38
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:schedule:log")
    public BananaResult list(@RequestParam Map<String, Object> params) {

        BananaPageUtils page = scheduleJobLogService.queryPage(params);

        return BananaResult.ok().put("page", page);
    }

    /**
     * @param '[logId]
     * @return com.burgess.banana.common.util.BananaResult
     * @class_name BananaScheduleJobLogController
     * @method info
     * @desc 定时任务日志信息
     * @author free.zhang
     * @date 2018/5/25 15:37
     */
    @GetMapping("/info/{logId}")
    public BananaResult info(@PathVariable("logId") Long logId) {

        BananaScheduleJobLogEntity log = scheduleJobLogService.selectById(logId);

        return BananaResult.ok().put("log", log);
    }
}

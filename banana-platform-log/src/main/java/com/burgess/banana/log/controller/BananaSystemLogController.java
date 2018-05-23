package com.burgess.banana.log.controller;

import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.log.service.BananaSystemLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.log.controller
 * @file BananaSystemLogController.java
 * @time 2018-05-23 16:32
 * @desc 系统日志Controller
 */
@Controller
@RequestMapping("/sys/log")
public class BananaSystemLogController {

    @Autowired
    private BananaSystemLogService sysLogService;

    /**
     * @class_name BananaSystemLogController
     * @method list
     * @desc 系统日志列表
     * @author free.zhang
     * @date 2018/5/23 16:32
     * @param '[params]
     * @return com.burgess.banana.common.util.BananaResult
     */
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:log:list")
    public BananaResult list(@RequestParam Map<String, Object> params){
        BananaPageUtils page = sysLogService.queryPage(params);

        return BananaResult.ok().put("page", page);
    }
}

package com.burgess.banana.config.controller;

import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.common.validator.BananaValidatorUtils;
import com.burgess.banana.config.entity.BananaSystemConfigEntity;
import com.burgess.banana.config.service.BananaSystemConfigService;
import com.burgess.banana.log.annotation.BananaSystemLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.controller
 * @file BananaSystemConfigController.java
 * @time 2018-05-23 21:55
 * @desc 系统配置Controller
 */
@RestController
@RequestMapping("/sys/config")
public class BananaSystemConfigController extends BananaAbstractController {

    @Resource(name = "sysConfigService")
    private BananaSystemConfigService sysConfigService;

    /**
     * 所有配置列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    public BananaResult list(@RequestParam Map<String, Object> params){
        BananaPageUtils page = sysConfigService.queryPage(params);

        return BananaResult.ok().put("page", page);
    }


    /**
     * 配置信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public BananaResult info(@PathVariable("id") Long id){
        BananaSystemConfigEntity config = sysConfigService.selectById(id);

        return BananaResult.ok().put("config", config);
    }

    /**
     * 保存配置
     */
    @BananaSystemLog("保存配置")
    @PostMapping("/save")
    @RequiresPermissions("sys:config:save")
    public BananaResult save(@RequestBody BananaSystemConfigEntity config){
        BananaValidatorUtils.validateEntity(config);

        sysConfigService.save(config);

        return BananaResult.ok();
    }

    /**
     * 修改配置
     */
    @BananaSystemLog("修改配置")
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public BananaResult update(@RequestBody BananaSystemConfigEntity config){
        BananaValidatorUtils.validateEntity(config);

        sysConfigService.update(config);

        return BananaResult.ok();
    }

    /**
     * 删除配置
     */
    @BananaSystemLog("删除配置")
    @PostMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public BananaResult delete(@RequestBody Long[] ids){
        sysConfigService.deleteBatch(ids);

        return BananaResult.ok();
    }
}

/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.burgess.banana.system.controller;


import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.common.validator.BananaValidatorUtils;
import com.burgess.banana.log.annotation.BananaSystemLog;
import com.burgess.banana.system.entity.SysConfigEntity;
import com.burgess.banana.system.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:55:53
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
	@Autowired
	private SysConfigService sysConfigService;
	
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
		SysConfigEntity config = sysConfigService.selectById(id);
		
		return BananaResult.ok().put("config", config);
	}
	
	/**
	 * 保存配置
	 */
	@BananaSystemLog("保存配置")
	@PostMapping("/save")
	@RequiresPermissions("sys:config:save")
	public BananaResult save(@RequestBody SysConfigEntity config){
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
	public BananaResult update(@RequestBody SysConfigEntity config){
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

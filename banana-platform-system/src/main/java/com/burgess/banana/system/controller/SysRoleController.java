package com.burgess.banana.system.controller;

import com.burgess.banana.common.util.BananaConstant;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.common.validator.BananaValidatorUtils;
import com.burgess.banana.log.annotation.BananaSystemLog;
import com.burgess.banana.system.entity.SysRoleEntity;
import com.burgess.banana.system.service.SysRoleMenuService;
import com.burgess.banana.system.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月8日 下午2:18:33
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 角色列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:role:list")
	public BananaResult list(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的角色列表
		if(getUserId() != BananaConstant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}

		BananaPageUtils page = sysRoleService.queryPage(params);

		return BananaResult.ok().put("page", page);
	}
	
	/**
	 * 角色列表
	 */
	@GetMapping("/select")
	@RequiresPermissions("sys:role:select")
	public BananaResult select(){
		Map<String, Object> map = new HashMap<>();
		
		//如果不是超级管理员，则只查询自己所拥有的角色列表
		if(getUserId() != BananaConstant.SUPER_ADMIN){
			map.put("createUserId", getUserId());
		}
		List<SysRoleEntity> list = sysRoleService.selectByMap(map);
		
		return BananaResult.ok().put("list", list);
	}
	
	/**
	 * 角色信息
	 */
	@GetMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public BananaResult info(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.selectById(roleId);
		
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		
		return BananaResult.ok().put("role", role);
	}
	
	/**
	 * 保存角色
	 */
	@BananaSystemLog("保存角色")
	@PostMapping("/save")
	@RequiresPermissions("sys:role:save")
	public BananaResult save(@RequestBody SysRoleEntity role){
		BananaValidatorUtils.validateEntity(role);
		
		role.setCreateUserId(getUserId());
		sysRoleService.save(role);
		
		return BananaResult.ok();
	}
	
	/**
	 * 修改角色
	 */
	@BananaSystemLog("修改角色")
	@PostMapping("/update")
	@RequiresPermissions("sys:role:update")
	public BananaResult update(@RequestBody SysRoleEntity role){
		BananaValidatorUtils.validateEntity(role);
		
		role.setCreateUserId(getUserId());
		sysRoleService.update(role);
		
		return BananaResult.ok();
	}
	
	/**
	 * 删除角色
	 */
	@BananaSystemLog("删除角色")
	@PostMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public BananaResult delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return BananaResult.ok();
	}
}

package com.burgess.banana.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.burgess.banana.common.exception.BananaResultException;
import com.burgess.banana.common.util.BananaConstant;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaQuery;
import com.burgess.banana.system.entity.BananaSystemRoleEntity;
import com.burgess.banana.system.mapper.BananaSystemRoleMapper;
import com.burgess.banana.system.service.BananaSystemRoleMenuService;
import com.burgess.banana.system.service.BananaSystemRoleService;
import com.burgess.banana.system.service.BananaSystemUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service.impl
 * @file BananaSystemRoleServiceImpl.java
 * @time 2018-05-23 21:43
 * @desc 角色service接口实现
 */
@Service("sysRoleService")
public class BananaSystemRoleServiceImpl extends ServiceImpl<BananaSystemRoleMapper, BananaSystemRoleEntity> implements BananaSystemRoleService {


    @Resource(name = "sysRoleMenuService")
    private BananaSystemRoleMenuService sysRoleMenuService;

    @Resource(name = "sysUserService")
    private BananaSystemUserService sysUserService;

    @Resource(name = "sysUserRoleService")
    private BananaSystemRoleService sysUserRoleService;


    @Override
    public BananaPageUtils queryPage(Map<String, Object> params) {
        String roleName = (String)params.get("roleName");
        Long createUserId = (Long)params.get("createUserId");

        Page<BananaSystemRoleEntity> page = this.selectPage(
                new BananaQuery<BananaSystemRoleEntity>(params).getPage(),
                new EntityWrapper<BananaSystemRoleEntity>()
                        .like(StringUtils.isNotBlank(roleName),"role_name", roleName)
                        .eq(createUserId != null,"create_user_id", createUserId)
        );

        return new BananaPageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BananaSystemRoleEntity role) {
        role.setCreateTime(new Date());
        this.insert(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BananaSystemRoleEntity role) {
        this.updateById(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.deleteBatchIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }


    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return baseMapper.queryRoleIdList(createUserId);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(BananaSystemRoleEntity role){
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if(role.getCreateUserId() == BananaConstant.SUPER_ADMIN){
            return ;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if(!menuIdList.containsAll(role.getMenuIdList())){
            throw new BananaResultException("新增角色的权限，已超出你的权限范围");
        }
    }
}

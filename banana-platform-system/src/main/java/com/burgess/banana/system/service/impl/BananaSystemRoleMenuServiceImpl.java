package com.burgess.banana.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.burgess.banana.system.entity.BananaSystemRoleMenuEntity;
import com.burgess.banana.system.mapper.BananaSystemRoleMenuMapper;
import com.burgess.banana.system.service.BananaSystemRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service.impl
 * @file BananaSystemRoleMenuServiceImpl.java
 * @time 2018-05-23 21:41
 * @desc 角色-菜单service接口实现
 */
@Service("sysRoleMenuService")
public class BananaSystemRoleMenuServiceImpl extends ServiceImpl<BananaSystemRoleMenuMapper, BananaSystemRoleMenuEntity> implements BananaSystemRoleMenuService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除角色与菜单关系
        deleteBatch(new Long[]{roleId});

        if (menuIdList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        List<BananaSystemRoleMenuEntity> list = new ArrayList<>(menuIdList.size());
        for (Long menuId : menuIdList) {
            BananaSystemRoleMenuEntity sysRoleMenuEntity = new BananaSystemRoleMenuEntity();
            sysRoleMenuEntity.setMenuId(menuId);
            sysRoleMenuEntity.setRoleId(roleId);

            list.add(sysRoleMenuEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}

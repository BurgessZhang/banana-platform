package com.burgess.banana.system.service.impl;

import com.burgess.banana.system.entity.BananaSysRoleMenu;
import com.burgess.banana.system.mapper.BananaSysRoleMenuMapper;
import com.burgess.banana.system.service.BananaSysRoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service.impl
 * @file_name BananaSysRoleMenuServiceImpl.java
 * @description
 * @create 2018-04-19 14:08
 */
@Service("bananaSysRoleMenuService")
public class BananaSysRoleMenuServiceImpl implements BananaSysRoleMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BananaSysRoleMenuServiceImpl.class);

    @Autowired
    private BananaSysRoleMenuMapper bananaSysRoleMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除就是与菜单关系
        deleteBatch(new Long[]{roleId});

        if (menuIdList.size() == 0){
            return;
        }
        //保存角色与菜单关系
        List<BananaSysRoleMenu> list = new ArrayList<>();
        BananaSysRoleMenu sysRoleMenu = null;
        for(Long menuId : menuIdList){
            sysRoleMenu = new BananaSysRoleMenu();

            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);

            list.add(sysRoleMenu);
            bananaSysRoleMenuMapper.insert(sysRoleMenu);
        }
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return bananaSysRoleMenuMapper.queryMenuIdList(roleId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return bananaSysRoleMenuMapper.deleteBatch(roleIds);
    }
}

package com.burgess.banana.system.service;

import java.util.List;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service
 * @file_name BananaSysRoleMenuService.java
 * @description 角色与菜单对应关系
 * @create 2018-04-19 13:31
 */
public interface BananaSysRoleMenuService {

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}

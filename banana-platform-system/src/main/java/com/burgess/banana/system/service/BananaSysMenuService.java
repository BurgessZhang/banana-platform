package com.burgess.banana.system.service;

import com.burgess.banana.system.entity.BananaSysMenu;
import com.burgess.banana.system.entity.dto.BananaSysMenuDto;

import java.util.List;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service
 * @file_name BananaSysMenuService.java
 * @description 菜单管理
 * @create 2018-04-19 13:20
 */
public interface BananaSysMenuService {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<BananaSysMenuDto> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<BananaSysMenuDto> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<BananaSysMenu> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<BananaSysMenu> getUserMenuList(Long userId);

    /**
     * 删除
     */
    void delete(Long menuId);
}

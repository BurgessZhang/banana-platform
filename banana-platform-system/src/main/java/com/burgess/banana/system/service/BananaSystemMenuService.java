package com.burgess.banana.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.burgess.banana.system.entity.BananaSystemMenuEntity;

import java.util.List;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service
 * @file BananaSystemMenuService.java
 * @time 2018-05-23 21:21
 * @desc
 */
public interface BananaSystemMenuService extends IService<BananaSystemMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    List<BananaSystemMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<BananaSystemMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<BananaSystemMenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<BananaSystemMenuEntity> getUserMenuList(Long userId);

    /**
     * 删除
     */
    void delete(Long menuId);
}

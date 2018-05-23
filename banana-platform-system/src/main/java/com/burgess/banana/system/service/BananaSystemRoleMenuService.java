package com.burgess.banana.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.burgess.banana.system.entity.BananaSystemRoleMenuEntity;

import java.util.List;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service
 * @file BananaSystemRoleMenuService.java
 * @time 2018-05-23 21:22
 * @desc
 */
public interface BananaSystemRoleMenuService extends IService<BananaSystemRoleMenuEntity> {

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

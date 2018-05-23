package com.burgess.banana.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.burgess.banana.system.entity.BananaSystemUserRoleEntity;

import java.util.List;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service
 * @file BananaSystemUserRoleService.java
 * @time 2018-05-23 21:24
 * @desc
 */
public interface BananaSystemUserRoleService extends IService<BananaSystemUserRoleEntity> {

    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}

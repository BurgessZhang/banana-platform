package com.burgess.banana.system.service;

import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.system.entity.BananaSysRole;

import java.util.List;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service
 * @file_name BananaSysRoleService.java
 * @description 角色
 * @create 2018-04-19 13:31
 */
public interface BananaSysRoleService {

    BananaPageUtils queryPage(Map<String, Object> params);

    void save(BananaSysRole role);

    void update(BananaSysRole role);

    void deleteBatch(Long[] roleIds);


    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}

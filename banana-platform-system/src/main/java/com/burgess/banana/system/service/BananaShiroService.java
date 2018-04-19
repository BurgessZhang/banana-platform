package com.burgess.banana.system.service;

import com.burgess.banana.system.entity.BananaSysUser;
import com.burgess.banana.system.entity.BananaSysUserToken;

import java.util.Set;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service
 * @file_name BananaShiroService.java
 * @description shiro相关接口
 * @create 2018-04-19 13:09
 */
public interface BananaShiroService {

    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    BananaSysUserToken queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    BananaSysUser queryUser(Long userId);
}

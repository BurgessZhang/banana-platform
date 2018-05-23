package com.burgess.banana.system.service;

import com.burgess.banana.system.entity.BananaSystemUserEntity;
import com.burgess.banana.system.entity.BananaSystemUserTokenEntity;

import java.util.Set;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service
 * @file BananaShiroService.java
 * @time 2018-05-23 21:17
 * @desc shiroservice接口
 */
public interface BananaShiroService {

    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    BananaSystemUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     */
    BananaSystemUserEntity queryUser(Long userId);
}

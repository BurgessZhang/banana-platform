package com.burgess.banana.system.service;

import com.burgess.banana.common.util.BananaResult;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service
 * @file_name BananaSysUserTokenService.java
 * @description 用户token
 * @create 2018-04-19 13:36
 */
public interface BananaSysUserTokenService {

    /**
     * 生成token
     * @param userId  用户ID
     */
    BananaResult createToken(long userId);

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(long userId);
}

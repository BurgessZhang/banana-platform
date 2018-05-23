package com.burgess.banana.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.system.entity.BananaSystemUserTokenEntity;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service
 * @file BananaSystemUserTokenService.java
 * @time 2018-05-23 21:26
 * @desc
 */
public interface BananaSystemUserTokenService extends IService<BananaSystemUserTokenEntity> {

    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    BananaResult createToken(long userId);

    /**
     * 退出，修改token值
     *
     * @param userId 用户ID
     */
    void logout(long userId);
}

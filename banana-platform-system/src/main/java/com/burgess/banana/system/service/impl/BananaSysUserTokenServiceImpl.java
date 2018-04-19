package com.burgess.banana.system.service.impl;

import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.system.service.BananaSysUserTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service.impl
 * @file_name BananaSysUserTokenServiceImpl.java
 * @description
 * @create 2018-04-19 13:39
 */
@Service("bananaSysUserTokenService")
public class BananaSysUserTokenServiceImpl implements BananaSysUserTokenService {


    private static final Logger LOGGER = LoggerFactory.getLogger(BananaSysUserTokenServiceImpl.class);

    @Override
    public BananaResult createToken(long userId) {
        return null;
    }

    @Override
    public void logout(long userId) {

    }
}

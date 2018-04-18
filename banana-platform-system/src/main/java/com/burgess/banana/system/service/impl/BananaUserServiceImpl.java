package com.burgess.banana.system.service.impl;

import com.burgess.banana.system.mapper.BananaUserMapper;
import com.burgess.banana.system.service.BananaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service.impl
 * @file_name BananaUserServiceImpl.java
 * @description 用户service接口实现
 * @create 2018-04-18 18:06
 */
@Service("bananaUserService")
public class BananaUserServiceImpl implements BananaUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BananaUserServiceImpl.class);

    @Autowired
    private BananaUserMapper bananaUserMapper;

}

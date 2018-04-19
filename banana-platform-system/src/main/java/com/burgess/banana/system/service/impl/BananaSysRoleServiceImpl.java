package com.burgess.banana.system.service.impl;

import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.system.entity.BananaSysRole;
import com.burgess.banana.system.service.BananaSysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service.impl
 * @file_name BananaSysRoleServiceImpl.java
 * @description
 * @create 2018-04-19 14:07
 */
@Service("bananaSysRoleService")
public class BananaSysRoleServiceImpl implements BananaSysRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BananaSysRoleServiceImpl.class);


    @Override
    public BananaPageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public void save(BananaSysRole role) {

    }

    @Override
    public void update(BananaSysRole role) {

    }

    @Override
    public void deleteBatch(Long[] roleIds) {

    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return null;
    }
}

package com.burgess.banana.system.service.impl;

import com.burgess.banana.system.service.BananaSysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service.impl
 * @file_name BananaSysUserRoleServiceImpl.java
 * @description
 * @create 2018-04-19 14:06
 */
@Service("bananaSysUserRoleService")
public class BananaSysUserRoleServiceImpl implements BananaSysUserRoleService {


    private static final Logger LOGGER = LoggerFactory.getLogger(BananaSysUserRoleServiceImpl.class);

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {

    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return null;
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return 0;
    }
}

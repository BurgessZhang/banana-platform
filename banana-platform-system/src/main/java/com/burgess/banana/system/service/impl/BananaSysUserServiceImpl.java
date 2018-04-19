package com.burgess.banana.system.service.impl;

import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.system.entity.BananaSysUser;
import com.burgess.banana.system.service.BananaSysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service.impl
 * @file_name BananaSysUserServiceImpl.java
 * @description
 * @create 2018-04-19 13:38
 */
@Service("bananaSysUserService")
public class BananaSysUserServiceImpl implements BananaSysUserService {


    private static final Logger LOGGER = LoggerFactory.getLogger(BananaSysUserServiceImpl.class);

    @Override
    public BananaPageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return null;
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return null;
    }

    @Override
    public BananaSysUser queryByUserName(String username) {
        return null;
    }

    @Override
    public void save(BananaSysUser user) {

    }

    @Override
    public void update(BananaSysUser user) {

    }

    @Override
    public void deleteBatch(Long[] userIds) {

    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        return false;
    }
}

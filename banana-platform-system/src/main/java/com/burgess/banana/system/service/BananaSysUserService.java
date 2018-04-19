package com.burgess.banana.system.service;

import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.system.entity.BananaSysUser;

import java.util.List;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service
 * @file_name BananaSysUserService.java
 * @description 系统用户
 * @create 2018-04-19 13:35
 */
public interface BananaSysUserService {

    BananaPageUtils queryPage(Map<String, Object> params);

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    BananaSysUser queryByUserName(String username);

    /**
     * 保存用户
     */
    void save(BananaSysUser user);

    /**
     * 修改用户
     */
    void update(BananaSysUser user);

    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    boolean updatePassword(Long userId, String password, String newPassword);
}

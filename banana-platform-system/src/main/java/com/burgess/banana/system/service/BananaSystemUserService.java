package com.burgess.banana.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.log.entity.BananaSystemUserEntity;

import java.util.List;
import java.util.Map;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service
 * @file BananaSystemUserService.java
 * @time 2018-05-23 21:25
 * @desc
 */
public interface BananaSystemUserService extends IService<BananaSystemUserEntity> {

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
    BananaSystemUserEntity queryByUserName(String username);

    /**
     * 保存用户
     */
    void save(BananaSystemUserEntity user);

    /**
     * 修改用户
     */
    void update(BananaSystemUserEntity user);

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

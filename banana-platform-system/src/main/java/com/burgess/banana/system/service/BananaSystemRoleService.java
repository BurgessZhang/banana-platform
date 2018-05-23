package com.burgess.banana.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.system.entity.BananaSystemRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service
 * @file BananaSystemRoleService.java
 * @time 2018-05-23 21:23
 * @desc
 */
public interface BananaSystemRoleService extends IService<BananaSystemRoleEntity> {

    BananaPageUtils queryPage(Map<String, Object> params);

    void save(BananaSystemRoleEntity role);

    void update(BananaSystemRoleEntity role);

    void deleteBatch(Long[] roleIds);


    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}

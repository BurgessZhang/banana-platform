package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysRole;

import java.util.List;

public interface BananaSysRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(BananaSysRole record);

    int insertSelective(BananaSysRole record);

    BananaSysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(BananaSysRole record);

    int updateByPrimaryKey(BananaSysRole record);

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
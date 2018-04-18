package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysRole;

public interface BananaSysRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(BananaSysRole record);

    int insertSelective(BananaSysRole record);

    BananaSysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(BananaSysRole record);

    int updateByPrimaryKey(BananaSysRole record);
}
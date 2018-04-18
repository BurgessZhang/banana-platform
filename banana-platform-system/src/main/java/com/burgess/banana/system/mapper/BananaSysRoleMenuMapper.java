package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysRoleMenu;

public interface BananaSysRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BananaSysRoleMenu record);

    int insertSelective(BananaSysRoleMenu record);

    BananaSysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BananaSysRoleMenu record);

    int updateByPrimaryKey(BananaSysRoleMenu record);
}
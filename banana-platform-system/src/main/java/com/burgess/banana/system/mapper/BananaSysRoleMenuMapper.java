package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysRoleMenu;

import java.util.List;

public interface BananaSysRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BananaSysRoleMenu record);

    int insertSelective(BananaSysRoleMenu record);

    BananaSysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BananaSysRoleMenu record);

    int updateByPrimaryKey(BananaSysRoleMenu record);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
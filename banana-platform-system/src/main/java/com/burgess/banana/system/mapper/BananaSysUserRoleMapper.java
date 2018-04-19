package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysUserRole;

import java.util.List;

public interface BananaSysUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BananaSysUserRole record);

    int insertSelective(BananaSysUserRole record);

    BananaSysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BananaSysUserRole record);

    int updateByPrimaryKey(BananaSysUserRole record);

    List<Long> queryRoleIdList(Long userId);

    int deleteBatch(Long[] roleIds);
}
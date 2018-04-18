package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysUser;

public interface BananaSysUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(BananaSysUser record);

    int insertSelective(BananaSysUser record);

    BananaSysUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(BananaSysUser record);

    int updateByPrimaryKey(BananaSysUser record);
}
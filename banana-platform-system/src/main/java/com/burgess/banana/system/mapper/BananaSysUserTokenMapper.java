package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysUserToken;

public interface BananaSysUserTokenMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(BananaSysUserToken record);

    int insertSelective(BananaSysUserToken record);

    BananaSysUserToken selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(BananaSysUserToken record);

    int updateByPrimaryKey(BananaSysUserToken record);
}
package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaUser;

public interface BananaUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(BananaUser record);

    int insertSelective(BananaUser record);

    BananaUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(BananaUser record);

    int updateByPrimaryKey(BananaUser record);
}
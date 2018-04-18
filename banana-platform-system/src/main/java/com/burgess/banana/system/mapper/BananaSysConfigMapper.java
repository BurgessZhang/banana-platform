package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysConfig;

public interface BananaSysConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BananaSysConfig record);

    int insertSelective(BananaSysConfig record);

    BananaSysConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BananaSysConfig record);

    int updateByPrimaryKey(BananaSysConfig record);
}
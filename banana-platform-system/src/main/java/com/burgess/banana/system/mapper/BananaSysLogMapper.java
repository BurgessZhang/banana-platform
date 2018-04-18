package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysLog;

public interface BananaSysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BananaSysLog record);

    int insertSelective(BananaSysLog record);

    BananaSysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BananaSysLog record);

    int updateByPrimaryKey(BananaSysLog record);
}
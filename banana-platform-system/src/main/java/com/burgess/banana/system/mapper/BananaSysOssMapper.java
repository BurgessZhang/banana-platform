package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysOss;

public interface BananaSysOssMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BananaSysOss record);

    int insertSelective(BananaSysOss record);

    BananaSysOss selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BananaSysOss record);

    int updateByPrimaryKey(BananaSysOss record);
}
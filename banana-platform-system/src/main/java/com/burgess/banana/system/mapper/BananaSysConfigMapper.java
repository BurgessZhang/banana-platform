package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysConfig;
import org.apache.ibatis.annotations.Param;

public interface BananaSysConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BananaSysConfig record);

    int insertSelective(BananaSysConfig record);

    BananaSysConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BananaSysConfig record);

    int updateByPrimaryKey(BananaSysConfig record);

    /**
     * 根据key，查询value
     */
    BananaSysConfig queryByKey(String paramKey);

    /**
     * 根据key，更新value
     */
    int updateValueByKey(@Param("key") String key, @Param("value") String value);
}
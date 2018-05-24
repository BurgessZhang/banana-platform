package com.burgess.banana.config.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.burgess.banana.config.entity.BananaSystemConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.config.mapper
 * @file BananaSystemConfigMapper.java
 * @time 2018-05-24 11:31
 * @desc 系统配置信息
 */
@Mapper
public interface BananaSystemConfigMapper extends BaseMapper<BananaSystemConfigEntity> {

    /**
     * 根据key，查询value
     */
    BananaSystemConfigEntity queryByKey(String paramKey);

    /**
     * 根据key，更新value
     */
    int updateValueByKey(@Param("key") String key, @Param("value") String value);
}

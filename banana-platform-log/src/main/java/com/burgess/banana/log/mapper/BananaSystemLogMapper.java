package com.burgess.banana.log.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.burgess.banana.log.entity.BananaSystemLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.log.mapper
 * @file BananaSystemLogMapper.java
 * @time 2018-05-23 16:21
 * @desc 系统日志
 */
@Mapper
public interface BananaSystemLogMapper extends BaseMapper<BananaSystemLogEntity> {
}

package com.burgess.banana.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.burgess.banana.system.entity.BananaSystemCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.dao
 * @file BananaSystemCaptchaMapper.java
 * @time 2018-05-23 17:14
 * @desc 验证码Mapper
 */
@Mapper
public interface BananaSystemCaptchaMapper extends BaseMapper<BananaSystemCaptchaEntity> {
}

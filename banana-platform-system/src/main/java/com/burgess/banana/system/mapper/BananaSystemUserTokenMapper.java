package com.burgess.banana.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.burgess.banana.system.entity.BananaSystemUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.dao
 * @file BananaSystemUserTokenMapper.java
 * @time 2018-05-23 17:58
 * @desc 系统用户Token
 */
@Mapper
public interface BananaSystemUserTokenMapper extends BaseMapper<BananaSystemUserTokenEntity> {

    BananaSystemUserTokenEntity queryByToken(String token);
}

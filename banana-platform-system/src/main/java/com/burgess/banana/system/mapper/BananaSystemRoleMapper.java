package com.burgess.banana.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.burgess.banana.system.entity.BananaSystemRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.dao
 * @file BananaSystemRoleMapper.java
 * @time 2018-05-23 17:53
 * @desc 角色Mapper
 */
@Mapper
public interface BananaSystemRoleMapper extends BaseMapper<BananaSystemRoleEntity> {

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}

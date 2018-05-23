package com.burgess.banana.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.burgess.banana.system.entity.BananaSystemUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.dao
 * @file BananaSystemUserRoleMapper.java
 * @time 2018-05-23 17:57
 * @desc 用户-角色关系
 */
@Mapper
public interface BananaSystemUserRoleMapper extends BaseMapper<BananaSystemUserRoleEntity> {

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);


    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}

package com.burgess.banana.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.burgess.banana.log.entity.BananaSystemUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.dao
 * @file BananaSystemUserMapper.java
 * @time 2018-05-23 17:56
 * @desc 系统用户Mapper
 */
@Mapper
public interface BananaSystemUserMapper extends BaseMapper<BananaSystemUserEntity> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    BananaSystemUserEntity queryByUserName(String username);
}

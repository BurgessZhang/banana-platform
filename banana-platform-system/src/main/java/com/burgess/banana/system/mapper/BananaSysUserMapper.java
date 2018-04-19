package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysUser;

import java.util.List;

public interface BananaSysUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(BananaSysUser record);

    int insertSelective(BananaSysUser record);

    BananaSysUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(BananaSysUser record);

    int updateByPrimaryKey(BananaSysUser record);

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    BananaSysUser queryByUserName(String username);
}
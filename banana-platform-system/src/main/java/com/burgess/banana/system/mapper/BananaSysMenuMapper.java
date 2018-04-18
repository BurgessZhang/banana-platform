package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysMenu;

public interface BananaSysMenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(BananaSysMenu record);

    int insertSelective(BananaSysMenu record);

    BananaSysMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(BananaSysMenu record);

    int updateByPrimaryKey(BananaSysMenu record);
}
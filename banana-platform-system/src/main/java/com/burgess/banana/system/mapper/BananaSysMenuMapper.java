package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysMenu;
import com.burgess.banana.system.entity.dto.BananaSysMenuDto;

import java.util.List;
import java.util.Map;

public interface BananaSysMenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(BananaSysMenu record);

    int insertSelective(BananaSysMenu record);

    BananaSysMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(BananaSysMenu record);

    int updateByPrimaryKey(BananaSysMenu record);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<BananaSysMenuDto> queryListParentId(Long parentId);

    List<BananaSysMenu> selectList(Map<String,Object> pMap);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<BananaSysMenu> queryNotButtonList();
}
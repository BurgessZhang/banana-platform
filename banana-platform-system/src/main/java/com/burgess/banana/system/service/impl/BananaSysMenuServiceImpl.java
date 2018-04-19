package com.burgess.banana.system.service.impl;

import com.burgess.banana.common.util.BananaConstant;
import com.burgess.banana.system.entity.BananaSysMenu;
import com.burgess.banana.system.entity.dto.BananaSysMenuDto;
import com.burgess.banana.system.mapper.BananaSysMenuMapper;
import com.burgess.banana.system.mapper.BananaSysRoleMenuMapper;
import com.burgess.banana.system.mapper.BananaSysUserMapper;
import com.burgess.banana.system.service.BananaSysMenuService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service.impl
 * @file_name BananaSysMenuServiceImpl.java
 * @description
 * @create 2018-04-19 14:09
 */
@Service("bananaSysMenuService")
public class BananaSysMenuServiceImpl implements BananaSysMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BananaSysMenuServiceImpl.class);

    @Autowired
    private BananaSysMenuMapper bananaSysMenuMapper;

    @Autowired
    private BananaSysUserMapper bananaSysUserMapper;

    @Autowired
    private BananaSysRoleMenuMapper bananaSysRoleMenuMapper;

    @Override
    public List<BananaSysMenuDto> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<BananaSysMenuDto> menuList = bananaSysMenuMapper.queryListParentId(parentId);
        if (CollectionUtils.isEmpty(menuIdList) || menuIdList.size() == 0){
            return menuList;
        }
        List<BananaSysMenuDto> userMenuList = new ArrayList<>();
        for(BananaSysMenuDto menu : menuList){
            if (menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<BananaSysMenuDto> queryListParentId(Long parentId) {
        return bananaSysMenuMapper.queryListParentId(parentId);
    }

    @Override
    public List<BananaSysMenu> queryNotButtonList() {
        return bananaSysMenuMapper.queryNotButtonList();
    }

    @Override
    public List<BananaSysMenu> getUserMenuList(Long userId) {
        //系统管理员拥有最高权限
        if (userId == BananaConstant.SUPER_ADMIN){
            return bananaSysMenuMapper.selectList(new HashMap<>(1));
        }
        //用户菜单列表
        List<Long> menuIdList = bananaSysUserMapper.queryAllMenuId(userId);

        return null;
    }

    @Override
    public void delete(Long menuId) {
        //删除菜单
        bananaSysMenuMapper.deleteByPrimaryKey(menuId);
        //删除菜单与角色的关联
        bananaSysRoleMenuMapper.deleteByPrimaryKey(menuId);
    }

    private List<BananaSysMenuDto> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<BananaSysMenuDto> menuDtoList = queryListParentId(0L,menuIdList);
        //地柜获取子菜单
        getMenuTreeList(menuDtoList,menuIdList);

        return menuDtoList;
    }

    /**
     * @class_name BananaSysMenuServiceImpl
     * @method getMenuTreeList
     * @description 递归
     * @author free.zhang
     * @date 2018/4/19 15:57
     * @param '[menuDtoList, menuIdList]
     * @return java.util.List<com.burgess.banana.system.entity.dto.BananaSysMenuDto>
     */
    private List<BananaSysMenuDto> getMenuTreeList(List<BananaSysMenuDto> menuDtoList,List<Long> menuIdList){
        List<BananaSysMenuDto> subMenuList = new ArrayList<>(1);
        for (BananaSysMenuDto menuDto : menuDtoList){
            //目录
            if (menuDto.getType() == BananaConstant.MenuType.CATALOG.getValue()){
                menuDto.setList(getMenuTreeList(queryListParentId(menuDto.getMenuId(),menuIdList),menuIdList));
            }
            subMenuList.add(menuDto);
        }
        return subMenuList;
    }
}

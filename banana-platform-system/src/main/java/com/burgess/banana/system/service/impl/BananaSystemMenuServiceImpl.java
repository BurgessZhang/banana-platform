package com.burgess.banana.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.burgess.banana.common.util.BananaConstant;
import com.burgess.banana.common.util.BananaMapUtils;
import com.burgess.banana.system.entity.BananaSystemMenuEntity;
import com.burgess.banana.system.mapper.BananaSystemMenuMapper;
import com.burgess.banana.system.service.BananaSystemMenuService;
import com.burgess.banana.system.service.BananaSystemRoleMenuService;
import com.burgess.banana.system.service.BananaSystemUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service.impl
 * @file BananaSystemMenuServiceImpl.java
 * @time 2018-05-23 21:37
 * @desc 系统菜单service接口实现
 */
@Service("sysMenuService")
public class BananaSystemMenuServiceImpl extends ServiceImpl<BananaSystemMenuMapper, BananaSystemMenuEntity> implements BananaSystemMenuService {


    @Resource(name = "sysUserService")
    private BananaSystemUserService sysUserService;

    @Resource(name = "sysRoleMenuService")
    private BananaSystemRoleMenuService sysRoleMenuService;

    @Override
    public List<BananaSystemMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<BananaSystemMenuEntity> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<BananaSystemMenuEntity> userMenuList = new ArrayList<>();
        for (BananaSystemMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<BananaSystemMenuEntity> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<BananaSystemMenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<BananaSystemMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if (userId == BananaConstant.SUPER_ADMIN) {
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public void delete(Long menuId) {
        //删除菜单
        this.deleteById(menuId);
        //删除菜单与角色关联
        sysRoleMenuService.deleteByMap(new BananaMapUtils().put("menu_id", menuId));
    }

    /**
     * 获取所有菜单列表
     */
    private List<BananaSystemMenuEntity> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<BananaSystemMenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<BananaSystemMenuEntity> getMenuTreeList(List<BananaSystemMenuEntity> menuList, List<Long> menuIdList) {
        List<BananaSystemMenuEntity> subMenuList = new ArrayList<BananaSystemMenuEntity>();

        for (BananaSystemMenuEntity entity : menuList) {
            //目录
            if (entity.getType() == BananaConstant.MenuType.CATALOG.getValue()) {
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}

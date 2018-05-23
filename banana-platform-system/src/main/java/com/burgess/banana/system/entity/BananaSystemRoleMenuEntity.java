package com.burgess.banana.system.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.entity
 * @file BananaSystemRoleMenuEntity.java
 * @time 2018-05-23 17:37
 * @desc 角色-菜单
 */
@TableName("sys_role_menu")
public class BananaSystemRoleMenuEntity implements Serializable {

    @TableId
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 设置：
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：角色ID
     *
     * @return Long
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置：菜单ID
     *
     * @param menuId 菜单ID
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取：菜单ID
     *
     * @return Long
     */
    public Long getMenuId() {
        return menuId;
    }
}

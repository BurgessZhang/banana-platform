package com.burgess.banana.system.entity.dto;

import com.burgess.banana.system.entity.BananaSysRole;

import java.io.Serializable;
import java.util.List;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.entity.dto
 * @file_name BananaSysRoleDto.java
 * @description
 * @create 2018-04-19 11:28
 */
public class BananaSysRoleDto extends BananaSysRole implements Serializable{

    /**菜单ID*/
    private List<Long> menuIdList;

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }
}

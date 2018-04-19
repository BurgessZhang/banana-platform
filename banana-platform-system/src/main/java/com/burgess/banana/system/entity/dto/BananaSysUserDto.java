package com.burgess.banana.system.entity.dto;

import com.burgess.banana.system.entity.BananaSysUser;

import java.io.Serializable;
import java.util.List;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.entity.dto
 * @file_name BananaSysUserDto.java
 * @description
 * @create 2018-04-19 11:26
 */
public class BananaSysUserDto extends BananaSysUser implements Serializable {

    /**角色ID列表*/
    private List<Long> roleIdList;

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}

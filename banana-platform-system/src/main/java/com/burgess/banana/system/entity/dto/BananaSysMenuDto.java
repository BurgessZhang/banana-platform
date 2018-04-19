package com.burgess.banana.system.entity.dto;

import com.burgess.banana.system.entity.BananaSysMenu;

import java.io.Serializable;
import java.util.List;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.entity.dto
 * @file_name BananaSysMenuDto.java
 * @description
 * @create 2018-04-19 11:30
 */
public class BananaSysMenuDto extends BananaSysMenu implements Serializable {

    private String parentName;

    private Boolean open;

    private List<?> list;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}

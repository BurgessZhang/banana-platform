package com.burgess.banana.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.burgess.banana.system.entity.BananaSystemMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.dao
 * @file BananaSystemMenuMapper.java
 * @time 2018-05-23 17:52
 * @desc 菜单管理
 */
@Mapper
public interface BananaSystemMenuMapper extends BaseMapper<BananaSystemMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<BananaSystemMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<BananaSystemMenuEntity> queryNotButtonList();
}

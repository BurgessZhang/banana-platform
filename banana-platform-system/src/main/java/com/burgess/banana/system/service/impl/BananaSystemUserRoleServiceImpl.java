package com.burgess.banana.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.burgess.banana.common.util.BananaMapUtils;
import com.burgess.banana.system.entity.BananaSystemUserRoleEntity;
import com.burgess.banana.system.mapper.BananaSystemUserRoleMapper;
import com.burgess.banana.system.service.BananaSystemUserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service.impl
 * @file BananaSystemUserRoleServiceImpl.java
 * @time 2018-05-23 21:46
 * @desc
 */
@Service("sysUserRoleService")
public class BananaSystemUserRoleServiceImpl extends ServiceImpl<BananaSystemUserRoleMapper, BananaSystemUserRoleEntity> implements BananaSystemUserRoleService {


    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除用户与角色关系
        this.deleteByMap(new BananaMapUtils().put("user_id", userId));

        if (roleIdList == null || roleIdList.size() == 0) {
            return;
        }

        //保存用户与角色关系
        List<BananaSystemUserRoleEntity> list = new ArrayList<>(roleIdList.size());
        for (Long roleId : roleIdList) {
            BananaSystemUserRoleEntity sysUserRoleEntity = new BananaSystemUserRoleEntity();
            sysUserRoleEntity.setUserId(userId);
            sysUserRoleEntity.setRoleId(roleId);

            list.add(sysUserRoleEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return baseMapper.queryRoleIdList(userId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}

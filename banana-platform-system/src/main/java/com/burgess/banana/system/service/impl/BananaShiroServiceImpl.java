package com.burgess.banana.system.service.impl;

import com.burgess.banana.common.util.BananaConstant;
import com.burgess.banana.system.entity.BananaSysMenu;
import com.burgess.banana.system.entity.BananaSysUser;
import com.burgess.banana.system.entity.BananaSysUserToken;
import com.burgess.banana.system.mapper.BananaSysMenuMapper;
import com.burgess.banana.system.mapper.BananaSysUserMapper;
import com.burgess.banana.system.mapper.BananaSysUserTokenMapper;
import com.burgess.banana.system.service.BananaShiroService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.service.impl
 * @file_name BananaShiroServiceImpl.java
 * @description
 * @create 2018-04-19 13:10
 */
@Service("bananaShiroService")
public class BananaShiroServiceImpl implements BananaShiroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BananaShiroServiceImpl.class);

    @Autowired
    private BananaSysMenuMapper bananaSysMenuMapper;

    @Autowired
    private BananaSysUserMapper bananaSysUserMapper;

    @Autowired
    private BananaSysUserTokenMapper bananaSysUserTokenMapper;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == BananaConstant.SUPER_ADMIN){
            List<BananaSysMenu> menuList = bananaSysMenuMapper.selectList(new HashMap<>(1));
            permsList = new ArrayList<>(menuList.size());
            for(BananaSysMenu menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = bananaSysUserMapper.queryAllPerms(userId);
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if (StringUtils.isNotBlank(perms)){
                permsSet.addAll(Arrays.asList(perms.trim().split(",")));
            }
        }

        return permsSet;
    }

    @Override
    public BananaSysUserToken queryByToken(String token) {
        return bananaSysUserTokenMapper.queryByToken(token);
    }

    @Override
    public BananaSysUser queryUser(Long userId) {
        return bananaSysUserMapper.selectByPrimaryKey(userId);
    }
}

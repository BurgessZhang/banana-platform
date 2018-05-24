package com.burgess.banana.system.service.impl;

import com.burgess.banana.common.util.BananaConstant;
import com.burgess.banana.log.entity.BananaSystemUserEntity;
import com.burgess.banana.system.entity.BananaSystemMenuEntity;
import com.burgess.banana.system.entity.BananaSystemUserTokenEntity;
import com.burgess.banana.system.mapper.BananaSystemMenuMapper;
import com.burgess.banana.system.mapper.BananaSystemUserMapper;
import com.burgess.banana.system.mapper.BananaSystemUserTokenMapper;
import com.burgess.banana.system.service.BananaShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.service.impl
 * @file BananaShiroServiceImpl.java
 * @time 2018-05-23 21:27
 * @desc shiroservice接口实现
 */
@Service("shiroService")
public class BananaShiroServiceImpl implements BananaShiroService {


    @Autowired
    private BananaSystemMenuMapper bananaSystemMenuMapper;
    @Autowired
    private BananaSystemUserMapper bananaSystemUserMapper;
    @Autowired
    private BananaSystemUserTokenMapper bananaSystemUserTokenMapper;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == BananaConstant.SUPER_ADMIN) {
            List<BananaSystemMenuEntity> menuList = bananaSystemMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (BananaSystemMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = bananaSystemUserMapper.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public BananaSystemUserTokenEntity queryByToken(String token) {
        return bananaSystemUserTokenMapper.queryByToken(token);
    }

    @Override
    public BananaSystemUserEntity queryUser(Long userId) {
        return bananaSystemUserMapper.selectById(userId);
    }
}

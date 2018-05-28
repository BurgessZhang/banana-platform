package com.burgess.banana.author.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.burgess.banana.author.entity.BananaSystemRoleEntity;
import com.burgess.banana.author.mapper.BananaSystemRoleMapper;
import com.burgess.banana.author.service.BananaSystemRoleService;
import org.springframework.stereotype.Service;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.author.service.impl
 * @file BananaSystemRoleServiceImpl.java
 * @time 2018-05-28 11:55
 * @desc
 */
@Service("sysRoleService")
public class BananaSystemRoleServiceImpl extends ServiceImpl<BananaSystemRoleMapper,BananaSystemRoleEntity> implements BananaSystemRoleService {
}

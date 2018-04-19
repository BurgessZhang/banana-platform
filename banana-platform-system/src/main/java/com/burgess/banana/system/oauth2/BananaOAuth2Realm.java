package com.burgess.banana.system.oauth2;

import com.burgess.banana.system.entity.BananaSysUser;
import com.burgess.banana.system.entity.BananaSysUserToken;
import com.burgess.banana.system.service.BananaShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.oauth2
 * @file_name BananaOAuth2Realm.java
 * @description 认证
 * @create 2018-04-19 11:11
 */
@Component
public class BananaOAuth2Realm extends AuthorizingRealm {

    @Resource(name = "bananaShiroService")
    private BananaShiroService bananaShiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof BananaOAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        BananaSysUser user = (BananaSysUser)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = bananaShiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        BananaSysUserToken tokenEntity = bananaShiroService.queryByToken(accessToken);
        //token失效
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        BananaSysUser user = bananaShiroService.queryUser(tokenEntity.getUserId());
        //账号锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}

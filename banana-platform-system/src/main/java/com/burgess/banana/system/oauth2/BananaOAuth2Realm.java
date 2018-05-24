package com.burgess.banana.system.oauth2;

import com.burgess.banana.log.entity.BananaSystemUserEntity;
import com.burgess.banana.system.entity.BananaSystemUserTokenEntity;
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
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.oauth2
 * @file BananaOAuth2Realm.java
 * @time 2018-05-23 22:12
 * @desc 认证
 */
@Component("oauth2Realm")
public class BananaOAuth2Realm extends AuthorizingRealm {


    @Resource(name = "shiroService")
    private BananaShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof BananaOAuth2Token;
    }

    /**
     * @file BananaOAuth2Realm.java
     * @method doGetAuthorizationInfo
     * @author burgess.zhang
     * @time 2018/5/23/023 22:14
     * @desc  授权(验证权限时调用)
     * @params '[principals]
     * @result org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        BananaSystemUserEntity user = (BananaSystemUserEntity)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * @file BananaOAuth2Realm.java
     * @method doGetAuthenticationInfo
     * @author burgess.zhang
     * @time 2018/5/23/023 22:15
     * @desc 认证(登录时调用)
     * @params '[token]
     * @result org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        BananaSystemUserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
        //token失效
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        BananaSystemUserEntity user = shiroService.queryUser(tokenEntity.getUserId());
        //账号锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}

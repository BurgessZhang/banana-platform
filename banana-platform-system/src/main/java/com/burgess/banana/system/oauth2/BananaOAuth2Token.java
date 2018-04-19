package com.burgess.banana.system.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.oauth2
 * @file_name BananaOAuth2Token.java
 * @description token
 * @create 2018-04-19 11:10
 */
public class BananaOAuth2Token implements AuthenticationToken{

    private String token;

    public BananaOAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

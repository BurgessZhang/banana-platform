package com.burgess.banana.system.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.oauth2
 * @file BananaOAuth2Token.java
 * @time 2018-05-23 22:17
 * @desc token
 */
public class BananaOAuth2Token implements AuthenticationToken {

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

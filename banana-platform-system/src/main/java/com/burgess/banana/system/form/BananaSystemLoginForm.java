package com.burgess.banana.system.form;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.form
 * @file BananaSystemLoginForm.java
 * @time 2018-05-23 17:41
 * @desc 登录表单
 */
public class BananaSystemLoginForm {

    private String username;
    private String password;
    private String captcha;
    private String uuid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

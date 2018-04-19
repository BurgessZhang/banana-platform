package com.burgess.banana.system.form;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.form
 * @file_name BananaSysLoginForm.java
 * @description 登录表单
 * @create 2018-04-19 11:35
 */
public class BananaSysLoginForm {

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

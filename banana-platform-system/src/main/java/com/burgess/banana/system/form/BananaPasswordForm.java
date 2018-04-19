package com.burgess.banana.system.form;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.form
 * @file_name BananaPasswordForm.java
 * @description 密码表单
 * @create 2018-04-19 11:36
 */
public class BananaPasswordForm {

    /**
     * 原密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

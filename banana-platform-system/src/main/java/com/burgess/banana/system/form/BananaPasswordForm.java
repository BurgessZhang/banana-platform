package com.burgess.banana.system.form;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.form
 * @file BananaPasswordForm.java
 * @time 2018-05-23 17:40
 * @desc 密码表单
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

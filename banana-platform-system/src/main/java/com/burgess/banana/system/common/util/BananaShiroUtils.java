package com.burgess.banana.system.common.util;

import com.burgess.banana.common.exception.BananaResultException;
import com.burgess.banana.system.entity.BananaSysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.utils
 * @file_name BananaShiroUtils.java
 * @description Shiro工具类
 * @create 2018-04-18 22:28
 */
public class BananaShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static BananaSysUser getUserEntity() {
        return (BananaSysUser)SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getUserEntity().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if(kaptcha == null){
            throw new BananaResultException("验证码已失效");
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }
}
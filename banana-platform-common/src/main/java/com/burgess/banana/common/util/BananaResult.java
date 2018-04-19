package com.burgess.banana.common.util;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.utils
 * @file_name R.java
 * @description 返回数据
 * @create 2018-04-18 22:31
 */
public class BananaResult extends HashMap<String, Object> {

    public BananaResult() {
        put("code", 0);
        put("msg", "success");
    }

    public static BananaResult error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static BananaResult error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static BananaResult error(int code, String msg) {
        BananaResult r = new BananaResult();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static BananaResult ok(String msg) {
        BananaResult r = new BananaResult();
        r.put("msg", msg);
        return r;
    }

    public static BananaResult ok(Map<String, Object> map) {
        BananaResult r = new BananaResult();
        r.putAll(map);
        return r;
    }

    public static BananaResult ok() {
        return new BananaResult();
    }

    public BananaResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}

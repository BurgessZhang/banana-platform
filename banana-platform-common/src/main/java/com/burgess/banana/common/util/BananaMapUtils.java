package com.burgess.banana.common.util;

import java.util.HashMap;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.utils
 * @file_name BananaMapUtils.java
 * @description Map工具类
 * @create 2018-04-18 22:35
 */
public class BananaMapUtils extends HashMap<String, Object> {

    private static final long serialVersionUID = 7791649406831230736L;

	@Override
    public BananaMapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

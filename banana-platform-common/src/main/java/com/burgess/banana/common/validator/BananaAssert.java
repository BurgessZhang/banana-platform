package com.burgess.banana.common.validator;

import com.burgess.banana.common.exception.BananaResultException;
import org.apache.commons.lang.StringUtils;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.validator
 * @file_name BananaAssert.java
 * @description 数据校验
 * @create 2018-04-18 22:52
 */
public  abstract class BananaAssert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BananaResultException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BananaResultException(message);
        }
    }

}

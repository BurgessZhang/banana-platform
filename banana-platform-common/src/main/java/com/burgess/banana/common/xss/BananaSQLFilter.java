package com.burgess.banana.common.xss;

import com.burgess.banana.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.xss
 * @file_name BananaSQLFilter.java
 * @description sql过滤
 * @create 2018-04-18 22:43
 */
public class BananaSQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new RRException("包含非法字符");
            }
        }

        return str;
    }
}

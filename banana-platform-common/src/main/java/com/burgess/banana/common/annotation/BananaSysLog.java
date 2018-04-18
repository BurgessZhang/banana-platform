package com.burgess.banana.common.annotation;

import java.lang.annotation.*;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.annotation
 * @file_name BananaSysLog.java
 * @description 系统日志注解
 * @create 2018-04-18 22:02
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BananaSysLog {

    String value() default "";

}

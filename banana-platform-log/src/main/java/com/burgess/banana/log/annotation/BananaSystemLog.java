package com.burgess.banana.log.annotation;

import java.lang.annotation.*;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.log.annotation
 * @file BananaSystemLog.java
 * @time 2018-05-23 16:08
 * @desc 系统日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BananaSystemLog {

    String value() default "";
}

package com.burgess.banana.datasources.annotation;

import java.lang.annotation.*;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.datasources.annotation
 * @file_name DataSource.java
 * @description 多数据源注解
 * @create 2018-04-18 18:13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String name() default "";
}

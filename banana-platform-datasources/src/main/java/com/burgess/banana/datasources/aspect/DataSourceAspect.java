package com.burgess.banana.datasources.aspect;

import com.burgess.banana.datasources.DataSourceNames;
import com.burgess.banana.datasources.DynamicDataSource;
import com.burgess.banana.datasources.annotation.DataSource;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.datasources.aspect
 * @file_name DataSourceAspect.java
 * @description 多数据源，切面处理类
 * @create 2018-04-18 18:14
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered{
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("@annotation(com.burgess.banana.datasources.annotation.DataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource ds = method.getAnnotation(DataSource.class);
        if(ds == null){
            DynamicDataSource.setDataSource(DataSourceNames.FIRST);
            LOGGER.debug("set datasource is " + DataSourceNames.FIRST);
        }else {
            DynamicDataSource.setDataSource(ds.name());
            LOGGER.debug("set datasource is " + ds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            LOGGER.debug("clean datasource");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}

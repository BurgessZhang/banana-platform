package com.burgess.banana.common.aspect;

import com.burgess.banana.common.exception.RRException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.aspect
 * @file_name BananaRedisAspect.java
 * @description Redis切面处理类
 * @create 2018-04-18 22:05
 */
@Aspect
@Configuration
public class BananaRedisAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(BananaRedisAspect.class);

    //是开redis缓存 true开启 false关闭
    @Value("${spring.redis.open: false}")
    private boolean open;

    public Object around(ProceedingJoinPoint point) throws Throwable{
        Object result = null;
        if (open){
            try {
                result = point.proceed();
            }catch (Exception e){
                LOGGER.error("redis error",e);
                throw new RRException("Redis服务异常");
            }
        }
        return result;
    }

}

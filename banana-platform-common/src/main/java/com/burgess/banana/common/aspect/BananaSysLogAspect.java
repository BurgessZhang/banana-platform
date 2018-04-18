package com.burgess.banana.common.aspect;

import com.burgess.banana.common.exception.RRException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.aspect
 * @file_name BananaSysLogAspect.java
 * @description 系统日志切面处理类
 * @create 2018-04-18 22:09
 */
@Aspect
@Component
public class BananaSysLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(BananaSysLogAspect.class);

    //是否开启redis缓存 true开 false关
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

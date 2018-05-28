package com.burgess.banana.redis.aspect;

import com.burgess.banana.common.exception.BananaResultException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.redis.aspect
 * @file BananaRedisAspect.java
 * @time 2018-05-24 13:37
 * @desc Redis切面处理类
 */
@Aspect
@Configuration
public class BananaRedisAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());
    //是否开启redis缓存  true开启   false关闭
    @Value("${spring.redis.open: false}")
    private boolean open;

    /**
     * @file BananaRedisAspect.java
     * @method around
     * @desc reids环绕通知
     * @author free.zhang
     * @date 2018/5/28 15:47
     * @param '[point]
     * @return java.lang.Object
     */
    @Around("execution(* com.burgess.banana.redis.util.BananaRedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(open){
            try{
                result = point.proceed();
            }catch (Exception e){
                logger.error("redis error", e);
                throw new BananaResultException("Redis服务异常");
            }
        }
        return result;
    }
}

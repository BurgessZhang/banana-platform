package com.burgess.banana.log.aspect;

import com.burgess.banana.common.entity.BananaSystemUserEntity;
import com.burgess.banana.common.util.BananaHttpContextUtils;
import com.burgess.banana.common.util.BananaIPUtils;
import com.burgess.banana.log.annotation.BananaSystemLog;
import com.burgess.banana.log.entity.BananaSystemLogEntity;
import com.burgess.banana.log.service.BananaSystemLogService;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.log.aspect
 * @file BananaSystemLogAspect.java
 * @time 2018-05-23 16:09
 * @desc 系统日志日志切面类
 */
@Aspect
@Component
public class BananaSystemLogAspect {

    @Autowired
    private BananaSystemLogService sysLogService;

    @Pointcut("@annotation(com.burgess.banana.log.annotation.BananaSystemLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        BananaSystemLogEntity sysLog = new BananaSystemLogEntity();
        BananaSystemLog syslog = method.getAnnotation(BananaSystemLog.class);
        if (syslog != null) {
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = new Gson().toJson(args[0]);
            sysLog.setParams(params);
        } catch (Exception e) {

        }

        //获取request
        HttpServletRequest request = BananaHttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(BananaIPUtils.getIpAddr(request));

        //用户名
        String username = ((BananaSystemUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
        sysLog.setUsername(username);

        sysLog.setTime(time);
        sysLog.setCreateDate(new Date());
        //保存系统日志
        sysLogService.insert(sysLog);
    }
}

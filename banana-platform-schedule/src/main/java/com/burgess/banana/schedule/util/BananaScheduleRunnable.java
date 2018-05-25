package com.burgess.banana.schedule.util;

import com.burgess.banana.common.exception.BananaResultException;
import com.burgess.banana.common.util.BananaSpringContextUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.schedule.util
 * @file ScheduleRunnable.java
 * @time 2018-05-25 15:51
 * @desc 执行定时任务
 */
public class BananaScheduleRunnable implements Runnable{

    private Object target;
    private Method method;
    private String params;

    public BananaScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = BananaSpringContextUtils.getBean(beanName);
        this.params = params;

        if(StringUtils.isNotBlank(params)){
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        }else{
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if(StringUtils.isNotBlank(params)){
                method.invoke(target, params);
            }else{
                method.invoke(target);
            }
        }catch (Exception e) {
            throw new BananaResultException("执行定时任务失败", e);
        }
    }
}

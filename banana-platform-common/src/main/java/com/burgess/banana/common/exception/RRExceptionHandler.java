package com.burgess.banana.common.exception;

import com.burgess.banana.common.utils.BananaResult;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.exception
 * @file_name RRExceptionHandler.java
 * @description 异常处理器
 * @create 2018-04-18 22:17
 */
@RestControllerAdvice
public class RRExceptionHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(RRExceptionHandler.class);

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public BananaResult handleRRException(RRException e){
        BananaResult r = new BananaResult();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());

        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public BananaResult handleDuplicateKeyException(DuplicateKeyException e){
        LOGGER.error(e.getMessage(), e);
        return BananaResult.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public BananaResult handleAuthorizationException(AuthorizationException e){
        LOGGER.error(e.getMessage(), e);
        return BananaResult.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public BananaResult handleException(Exception e){
        LOGGER.error(e.getMessage(), e);
        return BananaResult.error();
    }
}

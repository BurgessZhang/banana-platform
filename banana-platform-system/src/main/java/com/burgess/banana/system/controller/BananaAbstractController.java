package com.burgess.banana.system.controller;

import com.burgess.banana.system.entity.BananaSystemUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.controller
 * @file BananaAbstractController.java
 * @time 2018-05-23 21:53
 * @desc Controller公共组件
 */
public abstract class BananaAbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected BananaSystemUserEntity getUser() {
        return (BananaSystemUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}

package com.burgess.banana.common.validator.group;

import javax.validation.GroupSequence;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.validator.group
 * @file_name BananaGroup.java
 * @description 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 * @create 2018-04-18 23:00
 */
@GroupSequence({BananaAddGroup.class, BananaUpdateGroup.class})
public interface BananaGroup {
}

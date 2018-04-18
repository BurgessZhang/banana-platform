package com.burgess.banana.system.controller;

import com.burgess.banana.system.service.BananaUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.controller
 * @file_name BananaUserController.java
 * @description 用户Controller
 * @create 2018-04-18 18:07
 */
@RestController
@RequestMapping("/user")
public class BananaUserController {

    @Resource(name = "bananaUserService")
    private BananaUserService bananaUserService;

}

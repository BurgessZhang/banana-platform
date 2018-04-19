package com.burgess.banana.common.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.config
 * @file_name BananaMybatisPlusConfig.java
 * @description mybatis-plus配置
 * @create 2018-04-19 10:46
 */
@Configuration
public class BananaMybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

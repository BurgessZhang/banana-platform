package com.burgess.banana.system.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.config
 * @file BananaMybatisPlusConfig.java
 * @time 2018-05-23 22:27
 * @desc mybatis插件配置
 */
@Configuration
public class BananaMybatisPlusConfig {


    /**
     * @file BananaMybatisPlusConfig.java
     * @method paginationInterceptor
     * @author burgess.zhang
     * @time 2018/5/23/023 22:28
     * @desc 分页插件
     * @params '[]
     * @result com.baomidou.mybatisplus.plugins.PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        return new PaginationInterceptor();
    }
}

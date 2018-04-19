package com.burgess.banana.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.config
 * @file_name BananaCorsConfig.java
 * @description
 * @create 2018-04-19 10:40
 */
@Configuration
public class BananaCorsConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }
}

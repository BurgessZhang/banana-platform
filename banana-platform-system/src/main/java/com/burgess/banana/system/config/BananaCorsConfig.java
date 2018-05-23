package com.burgess.banana.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.config
 * @file BananaCorsConfig.java
 * @time 2018-05-23 22:25
 * @desc 请求方式配置
 */
@Configuration
public class BananaCorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }
}

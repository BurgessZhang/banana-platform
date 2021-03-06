package com.burgess.banana.system;

import com.burgess.banana.datasources.BananaDynamicDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({BananaDynamicDataSourceConfig.class})
public class BananaSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BananaSystemApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BananaSystemApplication.class);
	}
}

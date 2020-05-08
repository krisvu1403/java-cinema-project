package com.myproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@SuppressWarnings("deprecation")
public class AppConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*")
		.allowedOrigins("*")
		.allowCredentials(true)
		.maxAge(4800)
        .allowedMethods("POST", "GET", "PUT", "DELETE");
	}
}
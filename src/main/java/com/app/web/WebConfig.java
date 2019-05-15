package com.app.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("inicio");
		registry.addViewController("/contacto").setViewName("contacto");
		registry.addViewController("/sala").setViewName("sala");
		registry.addViewController("/login").setViewName("login");
	}

}

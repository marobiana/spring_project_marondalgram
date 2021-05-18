package com.marobiana.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // http://localhost/images/1_1620995857660/sun.png 와 같이 접근 가능하게 매핑해준다. 
		.addResourceLocations("file:///C:\\Users\\신보람\\Documents\\강의\\강의용프로젝트\\webProjectSNS\\images/"); // 실제 파일 저장 위치
	}
}

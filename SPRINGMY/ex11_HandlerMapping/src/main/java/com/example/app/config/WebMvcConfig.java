package com.example.app.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.app.handler.CustomHandler;
import com.example.app.interceptor.MemoInterceptor;

@Configuration
@EnableWebMvc
@ComponentScans({
	@ComponentScan("com.example.app.controller"),
	@ComponentScan("com.example.app.restController")
})
public class WebMvcConfig implements WebMvcConfigurer{

	@Bean
	public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20971520); 			// 20MB	//전체 업로드 허용 사이즈 (1024*1024*20)
        multipartResolver.setMaxUploadSizePerFile(20971520); 	// 20MB	//파일 1개당 허용가능한 업로드 사이즈
        multipartResolver.setMaxInMemorySize(20971520); 		// 20MB //캐시 공간
        return multipartResolver;
	}
	//ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	
	//Interceptor add
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(new MemoInterceptor()).addPathPatterns("/memo/*");	
		
	}
	//HANDLER MAPPING
	
	//1) BEAN MAPPING(BeanNameUrlHandlerMapping : 요청 URL을 동일한 이름을 가진 Bean에 매핑)
	@Bean
	BeanNameUrlHandlerMapping beanNameUrlHandlerMapping() {
		return new BeanNameUrlHandlerMapping();
		
	}
	@Bean("/custom_01")
	public CustomHandler customHandler () {
		return new CustomHandler();
	}
	
	//2) SimpleUrlHandlerMapping : 개발자가 직접 매핑정보를 설정 방식중 하나, 기본값 : 정적자원 매핑
	@Bean
	SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		
		Map<String,Object> urlMap= new HashMap();
		urlMap.put("/custom_02", new CustomHandler());
		handlerMapping.setUrlMap(urlMap);
		return handlerMapping;
	}
	
	
	//3) RequestMappingHandlerMapping
	
}

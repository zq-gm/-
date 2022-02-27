package com.zq.springboot.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public MyInterceptor getMyInterceptor() {
		return new MyInterceptor();
	}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	// 由于拦截器在bean实例化之前执行，所以拦截器执行时导致stringRedisTemplate没有注入。所以在拦截器执行时实例化拦截器bean.
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}

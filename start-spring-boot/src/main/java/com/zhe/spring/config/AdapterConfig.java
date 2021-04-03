package com.zhe.spring.config;

import com.zhe.spring.handle.SessionKeyValidateHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdapterConfig implements WebMvcConfigurer {

    @Bean
    public SessionKeyValidateHandle sessionKeyValidateHandle() {
        return new SessionKeyValidateHandle();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionKeyValidateHandle());
    }
}

package com.bliu.auth.config;

import com.bliu.auth.inceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] addPathPatterns = {"/api/user/**"};
        String[] excludePatterns = {"/api/user/login"};
        registry.addInterceptor(new AuthorizationInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePatterns);
    }
}

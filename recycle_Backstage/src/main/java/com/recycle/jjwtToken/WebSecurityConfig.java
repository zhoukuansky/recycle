package com.recycle.jjwtToken;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor()).addPathPatterns("/**")
                .excludePathPatterns
                        (
                                "/loginAndRegister/login",
                                "/loginAndRegister/register",
                                "/swagger-ui.html",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/recycle",
                                "/webjars/**"
                        );
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/webapp/");
        //System.out.println("*****************************");
//        registry.addResourceHandler("/ll/**")
//                .addResourceLocations("file:/webapps/recycle_front")
//                .addResourceLocations("file:/usr/apps/")
//                .addResourceLocations("file:C:/Users/HP/Desktop/recycle/recycle_front/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public UserInterceptor userInterceptor() {
        //System.out.println("*****************************");
        return new UserInterceptor();
    }


    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //System.out.println("*****************************");
        argumentResolvers.add(currentUserMethodArgumentResolver());
        //super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        //System.out.println("*****************************");
        return new CurrentUserMethodArgumentResolver();
    }
}
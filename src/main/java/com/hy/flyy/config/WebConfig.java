package com.hy.flyy.config;

import com.hy.flyy.entity.User;
import com.hy.flyy.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 黄勇
 * @since 2023/3/1
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UploadConfig uploadConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("/book/**").addResourceLocations("file:" + uploadConfig.getDir());
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(getUserLoginInterceptor());
        interceptorRegistration.addPathPatterns("/**").excludePathPatterns("/user/login"
                , "/**.html"
                , "/webjars/**"
                , "/swagger-resources/**"
                , "/csrf"
                , "/"
                , "/user/register"
                , "/user/captcha"
                , "/book/**"
                , "/upload"
        );
    }

    @Bean
    public UserLoginInterceptor getUserLoginInterceptor() {
        return new UserLoginInterceptor();
    }

    /*
     *addResourceHandler:访问映射路径
     *addResourceLocations:资源绝对路径
     */

}

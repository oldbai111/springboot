package com.jbwang.cwgl.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            //配置静态虚拟路径
            //本地静态资源映射
            registry.addResourceHandler("/img/**").addResourceLocations("file:D:\\IDEA\\WorkSpace\\cwgl\\src\\main\\resources\\static\\img\\");
        }

}

package com.nikki.shixunpro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.UUID;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 获取当前工作目录
        String currentDirectory = System.getProperty("user.dir");
        String savePath ="file:" + currentDirectory + "/shixunpro/src/main/resources/static/upload/";
        registry.addResourceHandler("/upload/**")
                .addResourceLocations(savePath);
    }
}

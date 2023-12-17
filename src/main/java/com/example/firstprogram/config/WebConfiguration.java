package com.example.firstprogram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final String resourcePath;
    private final String uploadpath;

    public WebConfiguration(@Value("${resource.path}")String resourcePath, @Value("${upload.path}") String uploadpath) {
        this.resourcePath = resourcePath;
        this.uploadpath = uploadpath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(uploadpath);
    }
}

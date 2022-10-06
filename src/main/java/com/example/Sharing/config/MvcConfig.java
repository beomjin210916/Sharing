package com.example.Sharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login/loginAndBack").setViewName("basic/index");
        registry.addViewController("/login/login").setViewName("login/login");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/login/signup").setViewName("login/signup");
    }
}

package com.projects.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ResourceConfigAdapter extends WebMvcConfigurerAdapter {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径
     
        String path = System.getProperty("user.dir")+"\\src\\main\\webapp\\assets\\upload\\";
        
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/assets/upload/**").
                    addResourceLocations("file:"+path);
        }else{//linux和mac系统
            registry.addResourceHandler("/assets/upload/**").
                    addResourceLocations("file:"+path);
        }
        super.addResourceHandlers(registry);
    }
}


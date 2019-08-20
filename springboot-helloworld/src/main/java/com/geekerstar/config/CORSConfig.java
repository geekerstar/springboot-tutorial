package com.geekerstar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author geekerstar
 * date: 2019-08-17 09:41
 * description:
 * <p>
 * 跨域配置
 */
@Configuration
public class CORSConfig extends WebMvcConfigurerAdapter {

    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有跨域访问
//        registry.addMapping("/**");
        registry.addMapping("/api/**")
                .allowedOrigins("https://wwww.geekerstar.com")
                .allowedMethods("POST", "GET");
    }
}

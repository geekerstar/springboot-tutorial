package com.geekerstar.config;

import com.geekerstar.interceptor.LoginValidationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author geekerstar
 * date: 2019-08-17 10:02
 * description:
 * 通过拦截器，我们可以针对特定 URI 做拦截，做相关业务处理，比如检查用户是否登录，打印每个请求的处理耗时等。
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginValidationInterceptor()).addPathPatterns("/admin/**");
    }
}

package com.geekerstar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author geekerstar
 * date: 2019-08-17 09:25
 * description:
 * <p>
 * 配置日期时间格式化
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 配置日期格式化
     *
     * @param formatterRegistry
     */
    public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }
}

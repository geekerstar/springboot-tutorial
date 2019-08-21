package com.geekerstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author geekerstar
 * date: 2019-08-21 09:52
 * description:
 */
@SpringBootApplication
public class PackageApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
        return springApplicationBuilder.sources(PackageApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(PackageApplication.class,args);
    }
}

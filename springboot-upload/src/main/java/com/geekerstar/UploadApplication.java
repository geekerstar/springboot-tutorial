package com.geekerstar;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author geekerstar
 * date: 2019-08-21 10:42
 * description:
 */
@SpringBootApplication
public class UploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class,args);
    }

    /**
     * 解决上传文件大于10M出现连接重置的问题。此异常内容 GlobalException 也捕获不到
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory tomcatEmbedded(){
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addConnectorCustomizers(connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)){
                // -1 means unlimited
                ((AbstractHttp11Protocol<?>)connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
                return tomcatServletWebServerFactory;
    }
}

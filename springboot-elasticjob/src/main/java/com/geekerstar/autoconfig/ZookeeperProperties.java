package com.geekerstar.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "elasticjob.zookeeper")
@Setter@Getter
public class ZookeeperProperties {
    //zookeeper地址列表
    private String serverList;
    //zookeeper命名空间
    private String namespace;

}

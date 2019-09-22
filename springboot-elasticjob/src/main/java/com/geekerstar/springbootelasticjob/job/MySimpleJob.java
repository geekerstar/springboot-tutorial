package com.geekerstar.springbootelasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.geekerstar.autoconfig.ElasticSimpleJob;
import com.geekerstar.springbootelasticjob.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
//@ElasticSimpleJob(
//        jobName = "mySimpleJob" ,
//        cron = "0/5 * * * * ?",
//        shardingTotalCount = 1,
//        overwrite = true
//)

public class MySimpleJob implements SimpleJob {
    @Autowired
    private OrderService orderService;

    @Override
    public void execute(ShardingContext shardingContext) {

        for (int i=0;i<10;i++){
            orderService.insertOrder();
        }

    }
}

package com.geekerstar.springbootelasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.geekerstar.springbootelasticjob.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

//@ElasticSimpleJob(
//        jobName = "thirdOrderProduceJob",
//        cron = "0/5 * * * * ?",
//        shardingTotalCount = 1,
//        overwrite = true
//)
public class ThirdOrderProduceJob implements SimpleJob {
    @Autowired
    private OrderService orderService;

    @Override
    public void execute(ShardingContext shardingContext) {
        orderService.produceThirdOrder();
    }
}

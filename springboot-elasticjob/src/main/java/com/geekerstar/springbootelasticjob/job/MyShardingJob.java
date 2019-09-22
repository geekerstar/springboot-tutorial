package com.geekerstar.springbootelasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.geekerstar.autoconfig.ElasticSimpleJob;
import com.geekerstar.springbootelasticjob.listener.MyNormalListener;
import com.geekerstar.springbootelasticjob.sharding.MyShardingStrategy;
import lombok.extern.slf4j.Slf4j;

@ElasticSimpleJob(
        jobName = "myShardingJob",
        cron = "0/10 * * * * ?",
        overwrite = true,
        shardingTotalCount = 10,
        jobStrategy = MyShardingStrategy.class,
        jobEvent = true,
        jobListner = {MyNormalListener.class,MyNormalListener.class}
)
@Slf4j
public class MyShardingJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("我是分片项："+shardingContext.getShardingItem());
    }
}

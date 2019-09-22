package com.geekerstar.springbootelasticjob.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

public class MyNormalListener  implements ElasticJobListener {
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        System.out.println("我是"+shardingContexts.getJobName()+"作业，在方法前！");
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        System.out.println("我是"+shardingContexts.getJobName()+"作业，在方法后！");
    }
}

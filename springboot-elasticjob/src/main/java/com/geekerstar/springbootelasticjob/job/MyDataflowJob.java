package com.geekerstar.springbootelasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.geekerstar.autoconfig.ElasticDataflowJob;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
//@ElasticDataflowJob(
//        jobName = "myDataflowJob",
//        cron = "0/10 * * * * ?",
//        shardingTotalCount = 2,
//        overwrite = true,
//        streamingProcess = true
//)
public class MyDataflowJob implements DataflowJob<Integer> {

    private List<Integer> list = new ArrayList<>();

    {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
    }

    @Override
    public List<Integer> fetchData(ShardingContext shardingContext) {
        List<Integer> rtnList = new ArrayList<>();
        //数字 % 分片总数 == 当前分片项
        for (Integer index : list){
            if (index % shardingContext.getShardingTotalCount() == shardingContext.getShardingItem()){
                rtnList.add(index);
                break;
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("我是分片项："+shardingContext.getShardingItem()+"，我是抓取的数据是："+rtnList);

        return rtnList;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Integer> data) {
        list.removeAll(data);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("我是分片项："+shardingContext.getShardingItem()+"我移除了数据："+data);
    }
}

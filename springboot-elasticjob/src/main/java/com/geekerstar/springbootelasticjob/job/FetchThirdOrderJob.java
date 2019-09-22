package com.geekerstar.springbootelasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.geekerstar.autoconfig.ElasticDataflowJob;
import com.geekerstar.springbootelasticjob.dao.JdOrderMapper;
import com.geekerstar.springbootelasticjob.dao.TmallOrderMapper;
import com.geekerstar.springbootelasticjob.model.AllOrder;
import com.geekerstar.springbootelasticjob.model.JdOrder;
import com.geekerstar.springbootelasticjob.model.TmallOrder;
import com.geekerstar.springbootelasticjob.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

//@ElasticDataflowJob(
//        jobName = "fetchThirdOrderJob",
//        cron = "0/15 * * * * ?",
//        shardingTotalCount = 2,
//        overwrite = true,
//        streamingProcess = false
//)
public class FetchThirdOrderJob implements DataflowJob<Object> {
    @Autowired
    private OrderService orderService;
    @Autowired
    private JdOrderMapper jdOrderMapper;
    @Autowired
    private TmallOrderMapper tmallOrderMapper;

    @Override
    public List<Object> fetchData(ShardingContext shardingContext) {
        //京东订单
        if (shardingContext.getShardingItem() == 0){
            List<JdOrder> jdOrders = jdOrderMapper.getNotFetchedOrder(5);
            if (jdOrders!=null&&jdOrders.size()>0){
                List<Object> jdOrderList = jdOrders.stream().map(jdOrder -> (Object) jdOrder).collect(toList());
                return jdOrderList;
            }
        }else {//天猫订单
            List<TmallOrder> tmallOrders = tmallOrderMapper.getNotFetchedOrder(5);
            if (tmallOrders!=null&&tmallOrders.size()>0){
                List<Object> tmallOrderList = tmallOrders.stream().map(tmallOrder -> (Object)tmallOrder).collect(toList());
                return tmallOrderList;
            }
        }
        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Object> data) {
        //京东订单
        if (shardingContext.getShardingItem() ==0){
            if (data!=null&&data.size()>0){
                List<JdOrder> jdOrders = data.stream().map(d -> (JdOrder) d).collect(toList());
                for (JdOrder jdOrder : jdOrders){
                    AllOrder allOrder = new AllOrder();
                    allOrder.setThirdOrderId(jdOrder.getId());
                    allOrder.setType(0);//京东订单
                    allOrder.setTotalAmount(jdOrder.getAmount());
                    allOrder.setCreateUser("system");
                    allOrder.setCreateTime(new Date());
                    allOrder.setUpdateUser("system");
                    allOrder.setUpdateTime(new Date());
                    orderService.processJdOrder(allOrder);
                }
            }
        }else {//天猫订单
            if (data!=null&&data.size()>0){
                List<TmallOrder> tmallOrders = data.stream().map(d -> (TmallOrder) d).collect(toList());
                for (TmallOrder tmallOrder : tmallOrders){
                    AllOrder allOrder = new AllOrder();
                    allOrder.setThirdOrderId(tmallOrder.getId());
                    allOrder.setType(1);//天猫订单
                    allOrder.setTotalAmount(tmallOrder.getMoney());
                    allOrder.setCreateUser("system");
                    allOrder.setCreateTime(new Date());
                    allOrder.setUpdateUser("system");
                    allOrder.setUpdateTime(new Date());
                    orderService.processTmallOrder(allOrder);
                }
            }
        }


    }
}

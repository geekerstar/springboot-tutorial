package com.geekerstar.springbootelasticjob.service;

import com.geekerstar.springbootelasticjob.dao.AllOrderMapper;
import com.geekerstar.springbootelasticjob.dao.JdOrderMapper;
import com.geekerstar.springbootelasticjob.dao.OrderMapper;
import com.geekerstar.springbootelasticjob.dao.TmallOrderMapper;
import com.geekerstar.springbootelasticjob.model.AllOrder;
import com.geekerstar.springbootelasticjob.model.JdOrder;
import com.geekerstar.springbootelasticjob.model.Order;
import com.geekerstar.springbootelasticjob.model.TmallOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private AllOrderMapper allOrderMapper;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private JdOrderMapper jdOrderMapper;
    @Autowired
    private TmallOrderMapper tmallOrderMapper;

    public int insertOrder(){
        Order order = new Order();
        order.setAmount(BigDecimal.TEN);
        order.setReceiveName("Green");
        order.setReceiveAddress("中国北京朝阳区xxx");
        order.setReceiveMobile("13811112222");
        order.setStatus(1);//未支付
        order.setCreateUser("Green");
        order.setCreateTime(new Date());
        order.setUpdateUser("Green");
        order.setUpdateTime(new Date());
        int i = orderMapper.insertSelective(order);
        return i;
    }

    public List<Order> getOrder(Calendar now, int shardingTotalCount, int shardingItem) {
        return orderMapper.getOrder(now.getTime(),shardingTotalCount,shardingItem);
    }

    public void cancelOrder(Integer orderId, Date updateTime, int status, String updateUser, Date updateNow) {
        orderMapper.cancelOrder(orderId,updateTime,status,updateUser,updateNow);
    }

    public void produceThirdOrder() {
        for (int i=0;i<5;i++){
            Random random = new Random();
            int randomInt = random.nextInt(2);
            //京东订单
            if (randomInt ==0){
                log.info("插入京东订单");
                JdOrder jdOrder = new JdOrder();
                jdOrder.setStatus(0);//未抓取
                jdOrder.setAmount(BigDecimal.TEN);
                jdOrder.setCreateUser("jdUser");
                jdOrder.setCreateTime(new Date());
                jdOrder.setUpdateUser("jdUser");
                jdOrder.setUpdateTime(new Date());
                jdOrderMapper.insertSelective(jdOrder);
            }else {//天猫订单
                log.info("插入天猫订单");
                TmallOrder tmallOrder = new TmallOrder();
                tmallOrder.setOrderStatus(0);//未抓取
                tmallOrder.setMoney(new BigDecimal(100));
                tmallOrder.setCreateUser("tmallUser");
                tmallOrder.setCreateTime(new Date());
                tmallOrder.setUpdateUser("tmallUser");
                tmallOrder.setUpdateTime(new Date());
                tmallOrderMapper.insertSelective(tmallOrder);
            }
        }
    }

    @Transactional
    public void processJdOrder(AllOrder allOrder) {
        allOrderMapper.insertSelective(allOrder);
        JdOrder jdOrder = new JdOrder();
        jdOrder.setId(allOrder.getThirdOrderId());
        jdOrder.setStatus(1);//已抓取
        jdOrder.setUpdateUser("system");
        jdOrder.setUpdateTime(new Date());
        jdOrderMapper.updateByPrimaryKeySelective(jdOrder);
    }

    @Transactional
    public void processTmallOrder(AllOrder allOrder) {
        allOrderMapper.insertSelective(allOrder);
        TmallOrder tmallOrder = new TmallOrder();
        tmallOrder.setId(allOrder.getThirdOrderId());
        tmallOrder.setOrderStatus(1);//已抓取
        tmallOrder.setUpdateUser("system");
        tmallOrder.setUpdateTime(new Date());
        tmallOrderMapper.updateByPrimaryKeySelective(tmallOrder);
    }
}

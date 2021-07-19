package com.geekerstar.rocketmq.model;

import com.geekerstar.rocketmq.constants.Const;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Producer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
    	String group_name = "test_model_producer_name";
        DefaultMQProducer producer = new DefaultMQProducer(group_name);
        producer.setNamesrvAddr(Const.NAMESRV_ADDR_MASTER_SLAVE);
        producer.start();

        for (int i = 0; i < 10; i++) {
            try {
            	String tag = (i % 2 ==0) ? "TagA":"TagB";
                Message msg = new Message("test_model_topic2",// topic
                		tag,// tag
                    ("信息内容" + i).getBytes()// body
                );
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            }
            catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        producer.shutdown();
    }
}

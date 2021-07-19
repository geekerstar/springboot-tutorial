package com.geekerstar.rocketmq.consumer.pull;

import com.geekerstar.rocketmq.constants.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class PullConsumer {
	//Map<key, value>  key为指定的队列，value为这个队列拉取数据的最后位置
    private static final Map<MessageQueue, Long> offseTable = new HashMap<MessageQueue, Long>();

    public static void main(String[] args) throws MQClientException {

    	String group_name = "test_pull_consumer_name";
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(group_name);
        consumer.setNamesrvAddr(Const.NAMESRV_ADDR_MASTER_SLAVE);
        consumer.start();
        System.err.println("consumer start");
        //	从TopicTest这个主题去获取所有的队列（默认会有4个队列）
        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("test_pull_topic");
        //	遍历每一个队列，进行拉取数据
        for (MessageQueue mq : mqs) {
            System.out.println("Consume from the queue: " + mq);

            SINGLE_MQ: while (true) {
                try {
                	//	从queue中获取数据，从什么位置开始拉取数据 单次对多拉取32条记录
                    PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                    System.out.println(pullResult);
                    System.out.println(pullResult.getPullStatus());
                    System.out.println();
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()) {
	                    case FOUND:
	                    	List<MessageExt> list = pullResult.getMsgFoundList();
	                    	for(MessageExt msg : list){
	                    		System.out.println(new String(msg.getBody()));
	                    	}
	                        break;
	                    case NO_MATCHED_MSG:
	                        break;
	                    case NO_NEW_MSG:
	                    	System.out.println("没有新的数据啦...");
	                        break SINGLE_MQ;
	                    case OFFSET_ILLEGAL:
	                        break;
	                    default:
	                        break;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        consumer.shutdown();
    }


    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offseTable.put(mq, offset);
    }


    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offseTable.get(mq);
        if (offset != null)
            return offset;
        return 0;
    }

}

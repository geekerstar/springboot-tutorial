package com.geekerstar.rabbitmq.client.simple;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 这是一个工作线程，相当于之前的消费者,轮询发消息
 */
public class Worker01 {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //消息接收
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody());
            System.out.println("接收到消息:" + receivedMessage);
        };
        //消息被取消
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "消费者取消消费接口回调逻辑");

        };
        System.out.println("C1 消费者启动等待消费.................. ");
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);

    }
}

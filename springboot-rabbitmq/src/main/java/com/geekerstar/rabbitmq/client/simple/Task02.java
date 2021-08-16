package com.geekerstar.rabbitmq.client.simple;

import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * 消息生产者,消息在手动应答时是不丢失的，放回队列重新消费
 */
public class Task02 {
    private static final String TASK_QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明队列
        channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入信息");
        while (sc.hasNext()) {
            String message = sc.nextLine();
            //发布消息
            channel.basicPublish("", TASK_QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("生产者发出消息" + message);
        }
    }

}

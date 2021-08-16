package com.geekerstar.rabbitmq.client.simple;

import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * 发送消息线程
 */
public class Task01 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtils.getChannel();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("消息发送完成：" + message);
        }

    }
}

package com.geekerstar.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class DelayedConsumer {
    @RabbitListener(queues = {"delayed.queue"})
    public void receiveMsg(Message message, Channel channel){
        String msg = new String(message.getBody());
        log.info("当前时间为{}，收到延迟消息为{}",new Date(), msg);
    }
}

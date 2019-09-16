package com.mq.demo.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author zeng
 * <p>
 * 简单队列消息消费者（消费者）
 */
@Component
@Slf4j
public class SimpleModeConsumer {

    @RabbitListener(queues = "${simple.mode.queue}")
    @RabbitHandler
    public void simpleModeConsumer(String message) {
        log.info("简单队列消息消费者："+message);
    }

}

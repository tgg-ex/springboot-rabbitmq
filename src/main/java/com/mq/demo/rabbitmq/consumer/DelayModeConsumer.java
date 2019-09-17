package com.mq.demo.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author zeng
 * <p>
 * 延迟接收消息（消费者）
 */
@Component
@Slf4j
public class DelayModeConsumer {

    @RabbitListener(queues = "worker-queue")
    @RabbitHandler
    public void simpleModeConsumer(String message) throws InterruptedException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("简单队列消息消费者：" + message + " 接收时间" + sf.format(new Date()));
    }

}

package com.mq.demo.rabbitmq.produce;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zeng
 * <p>
 * 发送延迟消息（生产者）
 */
@Component
@Slf4j
public class DelayModeProduce {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${delay.mode.queue}")
    private String queue;

    /**
     * 普通队列
     *
     * @param context 发送消息
     */
    public void sentSimpleMode(String context) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("简单队列消息生产者：" + context + "    发送时间" + sf.format(new Date()));
        rabbitTemplate.convertAndSend("delayedec", queue, context, message ->{
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message.getMessageProperties().setDelay(1000);
            return message;
        });
    }
}

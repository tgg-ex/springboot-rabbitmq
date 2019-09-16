package com.mq.demo.rabbitmq.produce;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zeng
 * <p>
 * 简单队列消息生产者（生产者）
 */
@Component
@Slf4j
public class SimpleModeProduce {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${simple.mode.queue}")
    private String queue;

    /**
     * 普通队列
     *
     * @param context 发送消息
     */
    public void sentSimpleMode(String context) {
        log.info("简单队列消息生产者：" + context);
        rabbitTemplate.convertAndSend(queue, context);
    }
}

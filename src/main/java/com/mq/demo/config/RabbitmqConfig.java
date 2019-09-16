package com.mq.demo.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zeng
 * <p>
 * Rabbitmq配置类
 */
@Configuration
public class RabbitmqConfig {

    @Value("${simple.mode.queue}")
    private String simpleModeQueue;

    @Value("${delay.mode.queue}")
    private String delayModeQueue;


    @Bean
    public Queue simpleModeQueue() {
        return new Queue(simpleModeQueue, true, false, false);
    }

    @Bean
    public Queue delayModeQueue() {
        return new Queue(delayModeQueue, true);
    }
}

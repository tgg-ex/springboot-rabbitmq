package com.mq.demo.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zeng
 * <p>
 * Rabbitmq配置类
 */
@Configuration
public class RabbitmqConfig {

    @Value("${simple.mode.queue}")
    private String simpleModeQueue;

    /**
     * 生产者将消息发送改交换机
     */
    public static final String EXCHANGE = "out.exchange";

    /**
     * 死信交换机"dlx.exchange"：buffer-queue"中产生死信后，会通过此交换机发送出去。
     **/
    public static final String DXL_EXCHANGE = "dlx.exchange";

    /**
     * 指定死信路由键参数（x-dead-letter-routing-key）
     */
    public final static String DLX_ROUTING_KEY = "dlx.routing.key";

    /**
     * 延迟队列
     */
    public final static String QUEUE = "buffer-queue";

    /**
     * 接收延迟队列，的消息
     */
    private final static String QUEUE2 = "worker-queue";

    /**
     * 将缓冲队列"buffer-queue"绑定到交换机"out.exchange"，路由键为"out.routing.key"
     */
    public final static String ROUTING_KEY = "out.routing.key";

    @Bean
    public Queue simpleModeQueue() {
        return new Queue(simpleModeQueue, true, false, false);
    }


    @Bean
    public Queue workerQueue() {
        return new Queue(QUEUE2, true, false, false);
    }

    @Bean
    public DirectExchange outExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(DXL_EXCHANGE, true, false);
    }

    @Bean
    public Queue delayQueue() {
        Map<String, Object> params = new HashMap<>();
        //指定死信交换机参数（x-dead-letter-exchange）
        params.put("x-dead-letter-exchange", DXL_EXCHANGE);
        //指定死信路由键参数（x-dead-letter-routing-key）
        params.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
        //创建延迟队列
        return new Queue(QUEUE, true, false, false, params);
    }

    @Bean
    public Binding outBinding() {
        return BindingBuilder.bind(delayQueue()).to(outExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(workerQueue()).to(dlxExchange()).with(DLX_ROUTING_KEY);
    }
}

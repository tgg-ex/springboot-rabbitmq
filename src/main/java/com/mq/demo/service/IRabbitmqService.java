package com.mq.demo.service;

/**
 * @author zeng
 * <p>
 * rabbitmq业务接口类
 */
public interface IRabbitmqService {

    /**
     * 往mq中发送简单消息
     *
     * @param message 发送消息
     */
    void sentSimpleMode(String message);

    /**
     * 往mq中发送延迟消息
     *
     * @param message 发送消息
     */
    void sentDelayMode(String message);
}

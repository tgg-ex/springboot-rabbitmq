package com.mq.demo.service.impl;

import com.mq.demo.rabbitmq.produce.DelayModeProduce;
import com.mq.demo.rabbitmq.produce.SimpleModeProduce;
import com.mq.demo.service.IRabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zeng
 * <p>
 * rabbitmq业务实现类
 */
@Service
public class RabbitmqServiceImpl implements IRabbitmqService {

    @Autowired
    private SimpleModeProduce simpleModeProduce;

    @Autowired
    private DelayModeProduce delayModeProduce;


    @Override
    public void sentSimpleMode(String message) {
        simpleModeProduce.sentSimpleMode(message);
    }

    @Override
    public void sentDelayMode(String message) {
        //TODO 还未完成，延迟队列功能
        delayModeProduce.sentSimpleMode(message);
    }
}

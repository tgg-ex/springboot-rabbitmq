package com.mq.demo.controller;

import com.mq.demo.service.IRabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zeng
 * <p>
 * rabbitmq测试控制器
 */
@RestController
@RequestMapping("/mq")
public class RabbitmqController {

    @Autowired
    private IRabbitmqService iRabbitmqService;

    @RequestMapping("/simpleMode")
    public String simpleMode(@RequestParam("message") String message) {
        iRabbitmqService.sentSimpleMode(message);
        return "ok";
    }

    @RequestMapping("/delayMode")
    public String delayMode(@RequestParam("message") String message) {
        //TODO 还未完成，延迟队列功能
        iRabbitmqService.sentDelayMode(message);
        return "ok";
    }
}

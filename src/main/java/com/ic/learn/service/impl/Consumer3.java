package com.ic.learn.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;


@Service("shijjConsumer")
public class Consumer3 implements MessageListener {
    private final Logger logger = LoggerFactory.getLogger(Consumer3.class);
    static {
        System.out.println("已经依赖成功");
    }
    @Override
    public void onMessage(Message message) {
        MessageProperties m = message.getMessageProperties();
        String msg = new String(message.getBody());
        System.out.println("3消费掉了："+msg+"------>>>");
        logger.info("consumer3 receive message------->:{}", message);
    }

}

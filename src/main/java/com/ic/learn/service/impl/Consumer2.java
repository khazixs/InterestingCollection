package com.ic.learn.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

/*第一个消费者, 接受direct的消息, 他的exchange为exchangeTest,  rout-key为queueTestKey*/
@Service("messageConsumer")
public class Consumer2 implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(Consumer2.class);

    static {
        System.out.println("已经依赖成功");
    }

    @Override
    public void onMessage(Message message) {
        MessageProperties m = message.getMessageProperties();
        String msg = new String(message.getBody());
        System.out.println("2消费掉了：" + msg + "------>>>");
        logger.info("consumer2 receive message------->:{}", message);
    }
}

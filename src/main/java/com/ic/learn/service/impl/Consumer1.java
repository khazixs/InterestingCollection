package com.ic.learn.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;
/*第二个消费者, 接受direct的消息(为了测试一个exchange可以发送多个消息), 他的exchange为exchangeTest,  rout-key为queueTestChris.*/
@Service("chrisConsumer")
public class Consumer1 implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(Consumer1.class);
    static {
        System.out.println("已经依赖成功");
    }
    @Override
    public void onMessage(Message message) {
        String msg = new String(message.getBody());
        System.out.println("1消费掉了："+msg+"------>>>");
        logger.info("consumer1 receive message------->:{}", message);
    }
}

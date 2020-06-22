package com.ic.userManagement.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)//表示该测试用例是运用junit4进行测试，也可以换成其他测试框架
@WebAppConfiguration //1 此注解指定web资源的位置，默认为src/main/webapp
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class TestRabbitMQ {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Test
    public void testRabbitMQ(){
        amqpTemplate.convertAndSend("topic_exchange","queue1.send","hello world");
    }
}

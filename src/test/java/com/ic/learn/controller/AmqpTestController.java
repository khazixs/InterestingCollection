package com.ic.learn.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("amqpTestController")
@RequestMapping("/amqpTest")
public class AmqpTestController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/sendMsg")
    @ResponseBody
    public String sendAmqpMsg(Model model, @RequestParam(value = "msg", defaultValue = "hello world!!!") String msg) {
        if (model != null && !"".equals(msg)) {
            amqpTemplate.convertAndSend("topic_exchange", "queue1.send", msg);
        } else {
            amqpTemplate.convertAndSend("topic_exchange", "queue1.send", "hello world!");
        }
        return "success";
    }

    @RequestMapping("/sendMsg2")
    @ResponseBody
    public String sendAmqpMsg2(Model model, @RequestParam(value = "msg", defaultValue = "hello world!!!") String msg) {
        if (model != null && !"".equals(msg)) {
            amqpTemplate.convertAndSend("topic_exchange", "queue2.send", "这个世界很奇妙！！");
        } else {
            amqpTemplate.convertAndSend("topic_exchange", "queue2.send", "这个世界很奇妙");
        }
        return "success";
    }

    @RequestMapping("/sendMsg3")
    @ResponseBody
    public String sendAmqpMsg3(Model model, @RequestParam(value = "msg", defaultValue = "hello world!!!") String msg) {
        if (model != null && !"".equals(msg)) {
            amqpTemplate.convertAndSend("direct_exchange", "queue3.send", "神奇的世界！！");
        } else {
            amqpTemplate.convertAndSend("direct_exchange", "queue3.send", "神奇的世界");
        }
        return "success";
    }
}

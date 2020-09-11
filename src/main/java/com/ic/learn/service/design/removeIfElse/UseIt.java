package com.ic.learn.service.design.removeIfElse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class UseIt {

    @Test
    public void test() {
        String name = "ming";
        Handler handler = PolicyFactory.getInvokeStrategy(name);
        handler.AAA(name);
    }
}

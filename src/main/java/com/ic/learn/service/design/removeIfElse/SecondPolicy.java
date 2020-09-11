package com.ic.learn.service.design.removeIfElse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Lazy(value = false)
@Component
public class SecondPolicy extends Handler implements InitializingBean {

    @Override
    public void BBB(String name) {
        System.out.println(name + "执行策略2的业务B");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        PolicyFactory.register("hong",this);
    }
}

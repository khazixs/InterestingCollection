package com.ic.learn.service.design.removeIfElse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Lazy(value = false)
public class FirstPolicy extends Handler implements InitializingBean {
    public FirstPolicy(){
        System.out.println(111);
    }
    @Override
    public void AAA(String name) {
        System.out.println(name + "执行策略1的业务A");
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        PolicyFactory.register("ming",this);
    }
}

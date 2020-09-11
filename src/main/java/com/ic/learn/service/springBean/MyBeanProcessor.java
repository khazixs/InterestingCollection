package com.ic.learn.service.springBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("++++++++++++MyBeanProcessor.postProcessBeforeInitialization()+++++++++++++++++");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("++++++++++++MyBeanProcessor.postProcessAfterInitialization()+++++++++++++++++");
        return bean;
    }
}

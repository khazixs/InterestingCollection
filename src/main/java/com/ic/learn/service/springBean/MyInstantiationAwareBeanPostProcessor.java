package com.ic.learn.service.springBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    /*实例化bean前调用*/
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("****************MyInstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation()************");
        return super.postProcessBeforeInstantiation(beanClass,beanName);
    }
    /*实例化bean后调用*/
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("****************MyInstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()************");
        return super.postProcessAfterInstantiation(bean, beanName);
    }
    /*设置某个属性调用*/
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("****************MyInstantiationAwareBeanPostProcessor.postProcessPropertyValues()************");
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
    /*初始化前调用*/
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("****************MyInstantiationAwareBeanPostProcessor.postProcessBeforeInitialization()************");
        return super.postProcessBeforeInitialization(bean, beanName);
    }
    /*初始化后调用*/
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("****************MyInstantiationAwareBeanPostProcessor.postProcessAfterInitialization()************");
        return super.postProcessAfterInitialization(bean, beanName);
    }
}

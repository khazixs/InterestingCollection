package com.ic.learn.others;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class TestByteTransfer implements BeanPostProcessor {
    public static void main(String[] args) {
        int i = -7;
        String res = Integer.toBinaryString(i);
        System.out.println((~(i-1))*-1);//计算机中负数是以补码形式表达的
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}

package com.ic.learn.service.springBean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class App {
    // TODO: 2020/9/3 学习bean生命周期
    public static void main(String[] args) {
        Resource resource = new ClassPathResource("classpath:spring/spring-beans.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        /*添加两个后置处理器*/
        beanFactory.addBeanPostProcessor(new MyBeanProcessor());
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        Object car = beanFactory.getBean("car");
        beanFactory.destroySingletons();
        System.out.println(car);
    }
}

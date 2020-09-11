package com.ic.learn.others.testSpring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class test01 implements BeanPostProcessor, BeanFactoryPostProcessor, InitializingBean, BeanNameAware, BeanFactoryAware {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
    /*------------------------------------------------------------------------------------------------*/

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
    /*------------------------------------------------------------------------------------------------*/

    @Override
    public void afterPropertiesSet() throws Exception {

    }
    /*------------------------------------------------------------------------------------------------*/

    @Override
    public void setBeanName(String name) {

    }
    /*------------------------------------------------------------------------------------------------*/

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}

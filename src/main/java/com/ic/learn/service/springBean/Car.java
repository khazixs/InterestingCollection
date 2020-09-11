package com.ic.learn.service.springBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {
    private String brand;

    public Car(){
        System.out.println("----构造函数-----Car()--------");
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private BeanFactory beanFactory;
    private String beanName;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("----BeanFactoryAware.setBeanFactory()--------");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("----BeanNameAware.setBeanName()--------");
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("----InitializingBean.afterPropertiesSet()--------");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("----DisposableBean.destroy()--------");
    }

}

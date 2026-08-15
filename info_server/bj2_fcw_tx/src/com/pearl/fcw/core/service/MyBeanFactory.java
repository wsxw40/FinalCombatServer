package com.pearl.fcw.core.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class MyBeanFactory implements BeanFactoryAware {
    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory bf) throws BeansException {
        beanFactory = bf;
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }

}

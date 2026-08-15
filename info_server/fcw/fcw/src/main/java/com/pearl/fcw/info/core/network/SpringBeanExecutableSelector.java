package com.pearl.fcw.info.core.network;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanExecutableSelector implements ExecutableSelector, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public SpringBeanExecutableSelector() {
    }

    public SpringBeanExecutableSelector(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Executable select(String path) {
        try {
            return (Executable) applicationContext.getBean(path);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}

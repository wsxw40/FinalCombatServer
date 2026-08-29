package com.pearl.fcw.info.core.persistence.dao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.config.ClassMetadata;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataConfig;

public class AbstractGenericDao<T extends BaseEntity> implements BeanFactoryAware {

    private BeanFactory beanFactory = null;

    protected Class<T> clazz;

    public AbstractGenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @SuppressWarnings("unchecked")
    protected <Y> Y getRouter() {
        ClassMetadata cm = ClassMetadataConfig.getClassMetadata(clazz);
        String routeBeanName="";
		try {
			routeBeanName = "schema" + Character.toTitleCase(cm.getSchema().charAt(0)) + cm.getSchema().substring(1);
		} catch (NullPointerException e) {
			System.err.println("Class:"+clazz.getSimpleName());
			throw e;
		}
        return (Y) beanFactory.getBean(routeBeanName);
    }

}

package com.pearl.fcw.core.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.ModelAndView;

public class DmController implements ApplicationContextAware, DisposableBean {
    //日志
    private Logger logger = LoggerFactory.getLogger(DmController.class);
    //上下文
    private static ApplicationContext applicationContext = null;

    @Override
    public void destroy() throws Exception {
        logger.debug("clear applicationContext");
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        applicationContext = arg0;
    }

    protected ModelAndView exception() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", null);
        return new ModelAndView("404", resultMap);
    }
}

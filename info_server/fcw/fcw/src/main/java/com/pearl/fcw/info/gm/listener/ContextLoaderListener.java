package com.pearl.fcw.info.gm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;

import com.pearl.fcw.info.lobby.utils.ConfigurationUtil;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, ConfigurationUtil.beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
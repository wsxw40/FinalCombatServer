package com.pearl.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.ServletConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pearl.o2o.pojo.Switch;
import com.pearl.service.NosqlService;



public class ServiceLocator {
	
	public static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
	public static  NosqlService nosqlService;
	public static  BeanFactory beanFactory;
	public static  boolean bjlogSwitch;
	public static final Logger xunleiLog = Logger.getLogger("xunleiLog");
	public static final Logger exception = Logger.getLogger("exception");
	public static DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void init(ServletConfig config){
		beanFactory=WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		nosqlService = (NosqlService)beanFactory.getBean("nosqlService");
		Switch bjlogSwitch=new Switch(true,"bjlogSwitch");
	}
}

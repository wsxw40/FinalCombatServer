package com.pearl.manager.utils;

import java.util.Iterator;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;

import com.pearl.manager.pojo.Switch;

public class ConfigurationUtil {
	private static final ConfigurationFactory factory = new ConfigurationFactory("propertyConfig.xml");
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationUtil.class);
	private static Configuration config ;
	
	static{
		try {
			config = factory.getConfiguration();
			System.out.println(config.toString());
		} catch (ConfigurationException e) {
			logger.error("fail to load key words file!!");
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static {
		logger.info("finishe loading configuration:");
		String temp = "";
		Iterator<String> iterator = config.getKeys();
		while(iterator.hasNext()){
			temp = iterator.next();
			logger.info(temp + " -->"+ config.getString(temp));
		}
	}
	public static final String LOGSERVER_XUNLEI_PORT = config.getString("logServerSocket.xunleiPort");
	public static final String LOGSERVER_LOG_PORT = config.getString("logServerSocket.logPort");
	public static final String APP_ROOT_PATH = config.getString("app.rootPath");
	public static String TEMPLATE_PATH = config.getString("template.path");
	public static final String SPRING_CONTEXT_PATH = config.getString("app.springPath");
	
	
	public static final String SOCKET_SNDA_PLAYERCOUNT_PORT = config.getString("socket.playercount.port");
	public static final int ASYN_TASK_POOLSIZE = config.getInt("app.asyntask.poolsize");
	
	//public static final BeanFactory beanFactory = new FileSystemXmlApplicationContext("file:" + SPRING_CONTEXT_PATH);
	public static BeanFactory beanFactory;
	
	//TODO  singleton
	public static final PerformanceDatas performanceDatas = new PerformanceDatas();
	
	//switch
	public static final Switch SWITCH_XUNLEI_LOG = new Switch(true, "xunlei_log");
	public static final Switch SWITCH_DYNAMIC_TEMPLATE = new Switch(false,"template_dynamic_swtich");
	public static final Switch SWITCH_PERFORMANCE_MONITOR = new Switch(false,"monitor_switch");
	public static final Switch SWITCH_INFO_LOG_MONITOR = new Switch(false,"infoLog");
	public static final Switch SWITCH_INFO = new Switch(true,"infoSwitch");
	//if is on, each request will check cancel array
	public static final Switch SWITCH_RPC_CANCEL = new Switch(false,"rpc_cancel_switch");
	public static final Switch SWITCH_PSNTOP_DYNAMIC = new Switch(true,"psntop_dynamic_switch");
	
	//time out for wait response from client
	public static int TIMEOUT_CLIENT = 1000 * 3;//ms;
	
	
	
}
	
	

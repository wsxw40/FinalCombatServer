package com.pearl.o2o.utils;

import java.util.Iterator;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.log4j.Logger;

public class ConfigurationUtil {
	private static final ConfigurationFactory factory = new ConfigurationFactory("propertyConfig.xml");
	private static final Logger logger = Logger.getLogger(ConfigurationUtil.class);
	private static Configuration config ;
	
	static{
		try {
			config = factory.getConfiguration();
		} catch (ConfigurationException e) {
			logger.fatal("fail to load key words file!!");
			throw new RuntimeException(e);
		}
	}
	public static final String APP_ROOT_PATH = config.getString("app.rootPath");
	public static String KEYWORD_FILE_PATH  = config.getString("keywords.file.path");
	public static String KEYWORD_REPLACE_CHAR = config.getString("keywords.replaceChar");
	public static String SDO_AREA_ID = config.getString("sdo.areaId");
	public static String SDO_GROUP_ID = config.getString("sdo.groupId");
	public static String SDO_UID_TYPE = config.getString("sdo.uidType");
	public static String SDO_APP_ID = config.getString("sdo.appId");
	public static String SDO_SEND_TIMEOUT = config.getString("sdo.send.timeout");
	public static String SDO_CONFIG_PATH = config.getString("sdo.configFilePath");
	public static String TEMPLATE_PATH = config.getString("template.path");
	public static final String APP_VERSION = config.getString("app.client.version");

	//TODO switch
	public static String sdo_switch = config.getString("sdo.switch");
	
	static {
		logger.info("finishe loading configuration:");
		String temp = "";
		Iterator<String> iterator = config.getKeys();
		while(iterator.hasNext()){
			temp = iterator.next();
			logger.info(temp + " -->"+ config.getString(temp));
		}
	}
	
	public static void main(String[] args) {
		
	}
}
	
	

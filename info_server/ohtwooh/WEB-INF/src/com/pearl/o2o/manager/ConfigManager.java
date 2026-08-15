package com.pearl.o2o.manager;


import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.log4j.Logger;

public class ConfigManager {
	static Logger log = Logger.getLogger(ConfigManager.class.getName());
	
	private static ConfigManager configManager;
	private ConfigurationFactory factory;
	private Configuration config;	
	
	//==============================
	//Configuration Properties
	//==============================
	public String ENCODING;
	
	public String MAIL_SMTP;
	public int 	  MAIL_PORT;
	public String MAIL_USERNAME;
	public String MAIL_PASSWORD;
	public String MAIL_FROM_ADDR;
	public String MAIL_FROM_NAME;
	
	public String ERROR_MAIL_TO_ADDR;
	public String ERROR_MAIL_TO_NAME;

	public String APPLICATION_FOLDER;
	public String APPLICATION_URL;

	
	//==============================
	//Methods
	//==============================		
	
	private ConfigManager(){		
		try{
			factory = new ConfigurationFactory("propertyConfig.xml");
			config = factory.getConfiguration();
			
			//Initialize mail server properties
			ENCODING			= config.getString("encoding");
			
			MAIL_SMTP 			= config.getString("mail.smtp");
			MAIL_PORT 			= config.getInt("mail.port");
			MAIL_USERNAME  		= config.getString("mail.username");
			MAIL_PASSWORD  		= config.getString("mail.password");
			MAIL_FROM_ADDR 		= config.getString("mail.from.addr");
			MAIL_FROM_NAME 		= config.getString("mail.from.name");	
			
			ERROR_MAIL_TO_ADDR	= config.getString("error.mail.to.addr");
			ERROR_MAIL_TO_NAME	= config.getString("error.mail.to.name");
			
			APPLICATION_FOLDER	= config.getString("application.folder");
			APPLICATION_URL		= config.getString("application.url");			
		}
		catch(ConfigurationException e){
			log.error("Can not initialize configuration.\n" +
					"Please check propertyConfig.xml under WEB-INF, and make sure configuration files are in proper place", e);
		}
	}
	
	public static ConfigManager  getInstance(){
		if(configManager==null){
			return new ConfigManager();
		}
		
		return configManager;
	}
	
	public static List getNotificationList(String type){
		return ConfigManager.getInstance().config.getList("notifications."+type+".role.name");
	}
	
	public static String getNotificationManagerFlag(String type){
		return ConfigManager.getInstance().config.getString("notifications."+type+".manager");
	}	
	
	public static String getNotificationTitle(String type){
		return ConfigManager.getInstance().config.getString("notifications."+type+".title");
	}
	
	public static String getNotificationTemplate(String type){
		return ConfigManager.getInstance().config.getString("notifications."+type+".template");
	}		
}

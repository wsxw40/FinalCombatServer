package com.pearl.o2o.utils;

import java.util.Iterator;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;

import com.pearl.o2o.pojo.Switch;
import com.pearl.o2o.socket.ChannelManager;

public class ConfigurationUtil {
	private static final ConfigurationFactory factory = new ConfigurationFactory("propertyConfig.xml");
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationUtil.class);
	private static Configuration config ;
	
	static{
		try {
			config = factory.getConfiguration();
		} catch (ConfigurationException e) {
			logger.error("fail to load key words file!!");
			throw new RuntimeException(e);
		}
	}
	//生化人列表
	public static final String BIO_LIST=config.getString("bio_list");

	public static final String APP_ROOT_PATH = config.getString("app.rootPath");
	public static String KEYWORD_REPLACE_CHAR = config.getString("keywords.replaceChar");
	public static final String SWITCH_SYNTODB = config.getString("switch.synCachedToDB");
	public static final String SWITCH_SEND_TEAM_RES = config.getString("switch.sendTeamRes");
	public static final String SWITCH_DAILYNUM = config.getString("switch.dailyNumToRedis");
	public static final String SWITCH_CRT_PSN_TOP = config.getString("switch.dailyInitPersonTop");
	public static final String SWITCH_TOPACTIVITY = config.getString("switch.topActivity");
	public static final String SWITCH_COMPETEBUY = config.getString("switch.competeBuy");
	public static final String SWITCH_COMPETEBUYSENDTASK=config.getString("switch.competeBuySendTask");
	public static String TEMPLATE_PATH = config.getString("template.path");
	public static final String APP_VERSION = config.getString("app.client.version");
	public static final String SPRING_CONTEXT_PATH = config.getString("app.springPath");
	public static final String SOCKET_PORT = config.getString("socket.port");
	public static final String SOCKET_GAMELISTEN_PORT = config.getString("socket.gamelisten.port");
	public static final String SOCKET_PROXY_IP = config.getString("socket.proxy.ip");
	public static final String SOCKET_SNDA_PLAYERCOUNT_PORT = config.getString("socket.playercount.port");
	public static final String LOTTERY_PATH = config.getString("log.lottery");
	public static final String XUNLEI_LOGIN_URL = config.getString("xunlei.login.url");
	public static final String FC_ONLINE_URL = config.getString("fc.online.url");
	public static final String XUNLEI_SERVER_IP = config.getString("xunlei.serverIP");
	public static final String XUNLEI_INFO_IP = config.getString("xunlei.info");
	static String str= config.getString("welcome");
	public static final String WELCOME_EMAIL=StringUtil.encoding(str);
	//logger parameter
	public static final String LOG_SOCKET_HOST = config.getString("logSocket.host");
	public static final int  LOG_SOCKET_PORT = StringUtil.toInt(config.getString("logSocket.port"));
	
	public static final int SOCKET_LOGIN_POOLSIZE = StringUtil.toInt(config.getString("socket.login.poolsize"));
	public static final int SOCKET_SERVER_POOLSIZE = StringUtil.toInt(config.getString("socket.server.poolsize"));
	public static final int SOCKET_CLIENT_POOLSIZE = StringUtil.toInt(config.getString("socket.client.poolsize"));
	public static final int SOCKET_PLAYER_POOLSIZE = StringUtil.toInt(config.getString("socket.player.poolsize"));
	public static final int SOCKET_GAME_POOLSIZE = StringUtil.toInt(config.getString("socket.game.poolsize"));
	
	public static final int ASYN_TASK_POOLSIZE = config.getInt("app.asyntask.poolsize");
	
	//public static final BeanFactory beanFactory = new FileSystemXmlApplicationContext("file:" + SPRING_CONTEXT_PATH);
	public static BeanFactory beanFactory;
	
	//TODO  singleton
	public static final PerformanceDatas performanceDatas = new PerformanceDatas();
	
	//switch
	public static final int logConfig = StringUtil.toInt(config.getString("switch.logConfig"));
	public static final int onlinegConfig = StringUtil.toInt(config.getString("switch.onlinegConfig"));

	public static final Switch SWITCH_XUNLEI_LOG = new Switch(logConfig==1?true:false, "xunlei_log");
	public static final Switch SWITCH_ONLINE = new Switch(onlinegConfig==1?true:false, "online_push");
	public static final Switch SWITCH_DYNAMIC_TEMPLATE = new Switch(false,"template_dynamic_swtich");
	public static final Switch SWITCH_PERFORMANCE_MONITOR = new Switch(false,"monitor_switch");
	public static final Switch SWITCH_INFO_LOG_MONITOR = new Switch(false,"infoLog");
	public static final Switch SWITCH_INFO = new Switch(true,"infoSwitch");
	//if is on, each request will check cancel array
	public static final Switch SWITCH_RPC_CANCEL = new Switch(false,"rpc_cancel_switch");
	public static final Switch SWITCH_PSNTOP_DYNAMIC = new Switch(true,"psntop_dynamic_switch");
	
	//init fc param
	public static final String DEFAULT_PLAYER_CR = config.getString("DEFAULT_PLAYER_CR");
	public static final String DEFAULT_PLAYER_RANK = config.getString("DEFAULT_PLAYER_RANK");
	public static final String DEFAULT_PLAYER_GP = config.getString("DEFAULT_PLAYER_GP");
	public static final String DEFAULT_PLAYER_V = config.getString("DEFAULT_PLAYER_V");
	public static final String DEFAULT_PLAYER_EXP = config.getString("DEFAULT_PLAYER_EXP");
	public static final String DEFAULT_PLAYER_MODULE = config.getString("DEFAULT_PLAYER_MODULE");
	public static final String DEFAULT_PLAYER_FIGHTNUM = config.getString("DEFAULT_PLAYER_FIGHTNUM");
	public static final String DEFAULT_COST_RELEASE_CHARACTER_SIZE = config.getString("DEFAULT_COST_RELEASE_CHARACTER_SIZE");
	public static final String DEFAULT_XUNLEI_VIP_GIFT = config.getString("DEFAULT_XUNLEI_VIP_GIFT");
	public static final String CACHE_TIMEOUT = config.getString("CACHE_TIMEOUT");
	public static final String CACHE_ITEM_TIMEOUT = config.getString("CACHE_ITEM_TIMEOUT");
	public static final String CACHE_TIMEOUT_DAY = config.getString("CACHE_TIMEOUT_DAY");
	public static final String CACHE_SYN_INTERVAL_SECONDS = config.getString("CACHE_SYN_INTERVAL_SECONDS");
	public static final String CREATE_TEAM_LIMIT = config.getString("CREATE_TEAM_LIMIT");
	public static final String JOIN_TEAM_LIMIT = config.getString("JOIN_TEAM_LIMIT");
	public static final String DURABLE_NOTIFY = config.getString("DURABLE_NOTIFY");
	public static final String DEFAULT_DB_SIZE = config.getString("DEFAULT_DB_SIZE");
	public static final String DEFAULT_DB_NUM = config.getString("DEFAULT_DB_NUM");
	
	public static final String AUTO_UNEQUIP = config.getString("AUTO_UNEQUIP");
	public static final String AUTO_DESTORY = config.getString("AUTO_DESTORY");
	public static final String DEFAULT_CMPT_BUY_STT_TIME_HOUR = config.getString("CMPT_BUY_STT_TIME_HOUR");
	public static final String DEFAULT_CMPT_BUY_END_TIME_HOUR = config.getString("CMPT_BUY_END_TIME_HOUR");
	public static final String DEFAULT_CMPT_BUY_STT_WEEK_DAY = config.getString("CMPT_BUY_STT_WEEK_DAY");
	public static final String DEFAULT_CMPT_BUY_END_WEEK_DAY = config.getString("CMPT_BUY_END_WEEK_DAY");
	public static final String DEFAULT_CMPT_BUY_OVR_TSK_TIME_HOUR=config.getString("CMPT_BUY_OVR_TSK_TIME_HOUR");
	public static final String DEFAULT_CMPT_BUY_SENDITEM_DELAY = config.getString("CMPT_BUY_SENDITEM_DELAY");
	public static final String DEFAULT_CMPT_BUY_SENDITEM_START_WEEK_DAY = config.getString("CMPT_BUY_SENDITEM_START_WEEK_DAY");
	
	
	public static final String DEFAULT_CMPT_RESWAR_STT_TIME_HOUR = config.getString("CMPT_RESWAR_STT_TIME_HOUR");
	public static final String DEFAULT_CMPT_RESWAR_STT_WEEK_DAY = config.getString("CMPT_RESWAR_STT_WEEK_DAY");
	public static final String DEFAULT_CMPT_RESWAR_END_TIME_HOUR = config.getString("CMPT_RESWAR_END_TIME_HOUR");
	public static final String DEFAULT_CMPT_RESWAR_END_WEEK_DAY = config.getString("CMPT_RESWAR_END_WEEK_DAY");	
	public static final String DEFAULT_CMPT_RESWAR_SEND_RES_WEEK_HOUR = config.getString("CMPT_RESWAR_SEND_RES_WEEK_HOUR");	
	public static final String DEFAULT_CMPT_RESWAR_SEND_RES_WEEK_DAY = config.getString("CMPT_RESWAR_SEND_RES_WEEK_DAY");	
	
	//analyser开关
	public static final String SWITCH_ANALYSER=config.getString("switch.analyser");
	public static final String SWITCH_IS_ADD_GPOINT=config.getString("switch.addgpoint");
	//资源争夺战排行榜开关
	public static final String TEAM_FIGHT_RES_PREWEEK = config.getString("TEAM_FIGHT_RES_PREWEEK");
	public static final String TEAM_RES_TOP = config.getString("TEAM_RES_TOP");
	//time out for wait response from client
	public static int TIMEOUT_CLIENT = 1000 * 3;//ms;
	
	public static final ChannelManager CHANNEL_HANDLERS = new ChannelManager();
	
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
	
	

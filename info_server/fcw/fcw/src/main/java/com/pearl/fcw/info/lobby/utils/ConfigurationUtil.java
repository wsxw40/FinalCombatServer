package com.pearl.fcw.info.lobby.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;

import com.pearl.fcw.info.lobby.pojo.Switch;
import com.pearl.fcw.info.lobby.pojo.SysSuit;

public class ConfigurationUtil {

    public static final Logger LOG = LoggerFactory.getLogger(ConfigurationUtil.class);

    private static Properties config = new Properties();

    public static BeanFactory beanFactory;

    @Value("")
    public static final AtomicLong PROPERTIES_FILE_LAST_MODIFIED = new AtomicLong(0);

    static {
        try {
            final File file = new File("./config/application.properties");
            LOG.info("load application.properties at{0}", file.getAbsolutePath());
            config.load(new FileInputStream(file));
            // 存储最后修改日期。
            PROPERTIES_FILE_LAST_MODIFIED.getAndSet(file.lastModified());

        } catch (Exception e) {
            LOG.error("fail to load application.properties!");
            throw new RuntimeException(e);
        }
    }

    public static final String ZONE_NAME = config.getProperty("zone.name");
    public static final String ZONE_ID = config.getProperty("zone.id");

    public static final String HTTP_SIGN_KEY = config.getProperty("http.sign.key");

    public static final int ASYN_TASK_POOLSIZE = Integer.parseInt(config.getProperty("thread.pool.size.asyn.task"));            // 异步任务使用的线程池大小
    public static final int SOCKET_LOGIN_POOLSIZE = Integer.parseInt(config.getProperty("thread.pool.size.socket.login"));      // 角色登陆（新建？）时使用的线程池大小
    public static final int SOCKET_SERVER_POOLSIZE = Integer.parseInt(config.getProperty("thread.pool.size.socket.server"));    // “s”接口使用的线程池大小
    public static final int SOCKET_CLIENT_POOLSIZE = Integer.parseInt(config.getProperty("thread.pool.size.socket.client"));    // “c”接口使用的线程池大小
    public static final int HTTP_CLIENT_TASK_POOLSIZE = Integer.parseInt(config.getProperty("thread.pool.size.http.client"));    // http client的线程池大小

    public static final int ORMCLIENT_INTERVAL_SECS = Integer.parseInt(config.getProperty("cache.flush.interval.secs"));
    public static final int ORMCLIENT_SCAN_INTERVAL_SECS = Integer.parseInt(config.getProperty("cache.scan.interval.secs"));
    public static final int ORMCLIENT_MAXDIRTY = Integer.parseInt(config.getProperty("cache.max.dirty"));

    public static final int SOCKET_PORT = Integer.parseInt(config.getProperty("port.rpc.socket"));
    public static final int WEB_APP_PORT = Integer.parseInt(config.getProperty("port.web.http"));
    public static final int WEB_CMD_PORT = Integer.parseInt(config.getProperty("port.cmd.socket"));

    public static final int DEFAULT_EXPR_SECS = Integer.parseInt(config.getProperty("cache.default.expr.secs"));

    public static final String DEFAULT_CMPT_BUY_STT_TIME_HOUR = config.getProperty("CMPT_BUY_STT_TIME_HOUR");
	public static final String DEFAULT_CMPT_BUY_END_TIME_HOUR = config.getProperty("CMPT_BUY_END_TIME_HOUR");
	public static final String DEFAULT_CMPT_BUY_STT_WEEK_DAY = config.getProperty("CMPT_BUY_STT_WEEK_DAY");
	public static final String DEFAULT_CMPT_BUY_END_WEEK_DAY = config.getProperty("CMPT_BUY_END_WEEK_DAY");
	public static final String DEFAULT_CMPT_BUY_OVR_TSK_TIME_HOUR=config.getProperty("CMPT_BUY_OVR_TSK_TIME_HOUR");
	public static final String DEFAULT_CMPT_BUY_SENDITEM_DELAY = config.getProperty("CMPT_BUY_SENDITEM_DELAY");
	public static final String DEFAULT_CMPT_BUY_SENDITEM_START_WEEK_DAY = config.getProperty("CMPT_BUY_SENDITEM_START_WEEK_DAY");
	
	
	public static final String DEFAULT_CMPT_RESWAR_STT_TIME_HOUR = config.getProperty("CMPT_RESWAR_STT_TIME_HOUR");
	public static final String DEFAULT_CMPT_RESWAR_STT_WEEK_DAY = config.getProperty("CMPT_RESWAR_STT_WEEK_DAY");
	public static final String DEFAULT_CMPT_RESWAR_END_TIME_HOUR = config.getProperty("CMPT_RESWAR_END_TIME_HOUR");
	public static final String DEFAULT_CMPT_RESWAR_END_WEEK_DAY = config.getProperty("CMPT_RESWAR_END_WEEK_DAY");	
	public static final String DEFAULT_CMPT_RESWAR_SEND_RES_WEEK_HOUR = config.getProperty("CMPT_RESWAR_SEND_RES_TIME_HOUR");	
	public static final String DEFAULT_CMPT_RESWAR_SEND_RES_WEEK_DAY = config.getProperty("CMPT_RESWAR_SEND_RES_WEEK_DAY");
	
	public static final String DEFAULT_RES_BATTLE_MATCH_MIN = config.getProperty("RES_BATTLE_MATCH_MIN");	
	
	public static final String SUIT_PROPERTIES=config.getProperty("suitProperties");

	public static final HashMap<Integer, SysSuit> SUITMAP=StringUtil.getDefaultSuit(SUIT_PROPERTIES);
	
	public static final String CACHE_TIMEOUT_DAY = config.getProperty("CACHE_TIMEOUT_DAY");
	public static final String CACHE_TIMEOUT = config.getProperty("CACHE_TIMEOUT");
	public static String KEYWORD_REPLACE_CHAR = config.getProperty("keywords.replaceChar");
	

	public static final int logConfig = StringUtil.toInt(config.getProperty("switch.logConfig"));
	public static final Switch SWITCH_XUNLEI_LOG = new Switch(logConfig==1?true:false, "xunlei_log");
	
	public static final String CACHE_ITEM_TIMEOUT = config.getProperty("CACHE_ITEM_TIMEOUT");
	
	public static final String DURABLE_NOTIFY = config.getProperty("DURABLE_NOTIFY");
	public static final String DEFAULT_DB_SIZE = config.getProperty("DEFAULT_DB_SIZE");




}

package com.pearl.manager.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.manager.manager.SmartyManager;
import com.pearl.manager.service.CreateService;
import com.pearl.manager.service.DeleteService;
import com.pearl.manager.service.GetService;
import com.pearl.manager.service.NosqlService;
import com.pearl.manager.service.UpdateService;

public class ServiceLocator {
	public static final GetService getService = (GetService) ConfigurationUtil.beanFactory.getBean("getService");
	public static final CreateService createService = (CreateService) ConfigurationUtil.beanFactory.getBean("createService");
	public static final UpdateService updateService = (UpdateService) ConfigurationUtil.beanFactory.getBean("updateService");
	public static final DeleteService deleteService = (DeleteService) ConfigurationUtil.beanFactory.getBean("deleteService");
	public static final NosqlService nosqlService = (NosqlService) ConfigurationUtil.beanFactory.getBean("nosqlService");
	public static final NosqlService nosqlMetaService = (NosqlService) ConfigurationUtil.beanFactory.getBean("nosqlMetaService");
	public static final ExecutorService asynTakService = Executors.newFixedThreadPool(ConfigurationUtil.ASYN_TASK_POOLSIZE);
	public static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
	public static final MemcachedClient mcc = (MemcachedClient) ConfigurationUtil.beanFactory.getBean("memcachedClient");
	public static final SmartyManager sm = new SmartyManager(ConfigurationUtil.TEMPLATE_PATH);
	public static final MemcachedLockImpl ml = new MemcachedLockImpl();
	public static final Logger nosqlLogger = LoggerFactory.getLogger("nosql");
	public static final Logger lotteryLogger = LoggerFactory.getLogger("lottery");
	public static Logger performanceLog = LoggerFactory.getLogger("request");
	public static Logger missionLog = LoggerFactory.getLogger("mission");
	public static Logger missionCompleteLog = LoggerFactory.getLogger("missionComplete");
	public static Logger syncacheLog = LoggerFactory.getLogger("syncache");
	public static Logger crtPsnTopLog = LoggerFactory.getLogger("createpersontop");
	public static Logger fileLog = LoggerFactory.getLogger("file");
	public static Logger infoLog = LoggerFactory.getLogger("infoLog");
	public static Logger debugInfo = LoggerFactory.getLogger("debugInfo");
	public static Logger exceptionLog = LoggerFactory.getLogger("exception");
	public static Logger rechargeLog = LoggerFactory.getLogger("recharge");
	public static Logger chargebackLog = LoggerFactory.getLogger("chargeback");
	public static Logger dailyCheckLog = LoggerFactory.getLogger("dailycheck");
	public static Logger dailyNumLog = LoggerFactory.getLogger("dailynum");
	public static Logger stageClearLog = LoggerFactory.getLogger("stage_clear");
	public static Logger vipSafecabinetLog = LoggerFactory.getLogger("vipsafecabinet");
	public static Logger magicBoxLog = LoggerFactory.getLogger("magicbox");
	
	public static Logger strengthLog = LoggerFactory.getLogger("Combine_strength");
	public static Logger slotLog = LoggerFactory.getLogger("Combine_slot");
	public static Logger insertLog = LoggerFactory.getLogger("Combine_insert");
	public static Logger convertLog = LoggerFactory.getLogger("Combine_convert");
	public static Logger removeLog = LoggerFactory.getLogger("Combine_remove");
	public static Logger growthMissionErrLog = LoggerFactory.getLogger("GrowthMissionErr");
	public static Logger growthMissionCompleteLog = LoggerFactory.getLogger("GrowthMissionComplete");
	public static Logger getLuckyPackageLog = LoggerFactory.getLogger("GetLuckyPackage");
	public static Logger checkCharacterData = LoggerFactory.getLogger("CheckCharacterData");
	public static Logger meltingLog = LoggerFactory.getLogger("melting");
}

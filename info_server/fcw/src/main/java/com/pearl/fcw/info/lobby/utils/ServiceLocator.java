package com.pearl.fcw.info.lobby.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.info.lobby.service.BaseService;

public class ServiceLocator {
    public static final ExecutorService asynTakService = Executors.newFixedThreadPool(ConfigurationUtil.ASYN_TASK_POOLSIZE);
    public static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    public static final ExecutorService asynLockService = Executors.newCachedThreadPool();

    public static final BaseService baseService = (BaseService) ConfigurationUtil.beanFactory.getBean("baseService");

    public static final Logger scheduleLogger = LoggerFactory.getLogger("schedule");
    public static final Logger interfaceLogger = LoggerFactory.getLogger("interface");
    public static final Logger stageQuitLogger = LoggerFactory.getLogger("stageQuit");
    public static final Logger synLogger = LoggerFactory.getLogger("syn");
}

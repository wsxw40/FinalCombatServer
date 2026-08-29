package com.pearl.fcw.core.service;

import java.util.Random;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.cache.CacheKey;
import com.pearl.fcw.core.cache.TransactionManager;
import com.pearl.fcw.core.pojo.DelayMessage;
import com.pearl.o2o.utils.BinaryChannelBuffer;

@Aspect
@Component
@Service
public class ClientListener {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CacheKey cacheKey;
    @Resource
    private TransactionManager transactionManager;
    @Resource
    private Delayed delayed;

    @Around("execution(*  com.pearl.fcw.core.service.Servletable+.rpc(*))")
    public Object rpc(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
		String[] params = (String[]) args[0];
		String key = ((Servletable) joinPoint.getTarget()).getLockedKey(params);
        Object obj = null;
        logger.debug("Client rpc start.");
        if (null != key) {
            transactionManager.associate(cacheKey.getDistributeLockedKey(key));
        }

        try {
			obj = joinPoint.proceed(args);
			DelayMessage delayMsg = new DelayMessage();
			delayMsg.setKey(key);
			delayMsg.setUri(params[params.length - 1]);
			delayed.addMsg(delayMsg);
            delayed.delay();
        } catch (Throwable e) {
            if (null != key) {
                transactionManager.rollback();
            }
            logger.debug("Client rpc rollback.");
            throw e;
        }

        if (null != key) {
            transactionManager.commit();
        }
        logger.debug("Client rpc end.");
        return obj;
    }

    @Around("execution(*  com.pearl.fcw.core.service.Servletable+.server(*))")
    public Object server(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        BinaryChannelBuffer r = ((BinaryChannelBuffer) args[0]).clone();
        String key = ((Servletable) joinPoint.getTarget()).getLockedKey(r);
        Object obj = null;
        logger.debug("Client proxy start.");
        if (null == key) {
            key = new Random().nextLong() + "";
        }
        transactionManager.associate(cacheKey.getDistributeLockedKey(key));

        try {
            obj = joinPoint.proceed(args);
			DelayMessage delayMsg = new DelayMessage();
			delayMsg.setKey(key);
			delayed.addMsg(delayMsg);
            delayed.delay();
        } catch (Throwable e) {
            if (null != key) {
                transactionManager.rollback();
            }
            logger.debug("Client proxy rollback.");
            throw e;
        }

        if (null != key) {
            transactionManager.commit();
        }
        logger.debug("Client proxy end.");
        return obj;
    }
}

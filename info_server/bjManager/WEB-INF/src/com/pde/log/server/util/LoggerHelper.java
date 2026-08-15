/**
 * 
 */
package com.pde.log.server.util;

import java.util.List;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.spi.FilterReply;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.pde.log.common.LogMessage.LogMsg;

/**
 * @author lifengyang
 *
 */
public class LoggerHelper {
//	public static final LoadingCache<String, Logger> loggerCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Logger>() {
//		@Override
//		public Logger load(String name) throws Exception {
//			return (Logger) LoggerFactory.getLogger(name);
//		}
//	});

	public static void log(Logger logger, LogMsg logMsg) {
		try {
			
			Marker marker = null;
			String fqcn = Logger.FQCN;
			int levelInt = logMsg.getLevel().getNumber();
			String message = logMsg.getMsg();
			Object[] argArray = null;
			Throwable t = null;

			Level level = Level.fromLocationAwareLoggerInteger(levelInt);
			FilterReply decision = logger.getLoggerContext().getTurboFilterList().size() == 0 ? FilterReply.NEUTRAL : logger.getLoggerContext().getTurboFilterList()
					.getTurboFilterChainDecision(marker, logger, level, message, argArray, t);

			if (decision == FilterReply.NEUTRAL) {
				if (logger.getEffectiveLevel().levelInt > level.levelInt) {
					return;
				}
			} else if (decision == FilterReply.DENY) {
				return;
			}
			LoggingEvent le = new LoggingEvent(fqcn, logger, level, message, t, argArray);
			le.setMarker(marker);
			le.setTimeStamp(logMsg.getTime());
			logger.callAppenders(le);
		} catch (Exception e) {
			Constants.logServerLogger.error("LogServerHandle.log", e);
		}
		
	}

	public static void log(LogMsg logMsg) {
		try {
			log((Logger) LoggerFactory.getLogger(logMsg.getLogger()), logMsg);
		} catch (Exception e) {
			Constants.logServerLogger.error("LogServerHandle.log", e);
		}
	}

	public static void logs(List<LogMsg> logs) {
		LinkedListMultimap<String, LogMsg> multimap = LinkedListMultimap.<String, LogMsg>create();
//		HashMultimap<String, LogMsg> multimap = HashMultimap.<String, LogMsg> create();
		for (LogMsg logMsg : logs) {
			multimap.put(logMsg.getLogger(), logMsg);
		}
		Set<String> keySet = multimap.keySet();
		for (String name : keySet) {
			Logger logger = (Logger) LoggerFactory.getLogger(name);
			//Set<LogMsg> set = multimap.get(name);
			List<LogMsg> list = multimap.get(name);
			for (LogMsg logMsg : list) {
				log(logger, logMsg);
			}
		}
	}
}

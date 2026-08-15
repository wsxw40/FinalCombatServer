package com.pearl.o2o.servlet.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.schedule.SynCacheWithDBTask;
import com.pearl.o2o.utils.ServiceLocator;
public class SynCacheServletByMemcache extends HttpServlet {

	private static final long serialVersionUID = -222895462886969191L;
	static Logger log = LoggerFactory.getLogger(SynCacheServletByMemcache.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		try {
			log.info("manual Syn Cache DB Task start");
			SynCacheWithDBTask.isManual=true;
			new SynCacheWithDBTask().run();
		} catch (Exception e) {
			ServiceLocator.nosqlLogger.error("run syncache db task happened error",e);
		}
	}
}
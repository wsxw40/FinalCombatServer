package com.pearl.o2o.servlet.server;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.schedule.SynCacheWithDBTask;
import com.pearl.o2o.utils.ServiceLocator;
public class SynCacheServlet extends HttpServlet {

	private static final long serialVersionUID = -222895462886969191L;
	static Logger log = LoggerFactory.getLogger(SynCacheServlet.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		try {
			String isMemcached = req.getParameter("ismemcached");
			log.info("manual Syn Cache DB Task start");
			SynCacheWithDBTask.isManual=true;
			if("Y".equals(isMemcached)){
				new SynCacheWithDBTask().run();
			}else{
				new SynCacheWithDBTask().run();
			}
			
			
		} catch (Exception e) {
			ServiceLocator.nosqlLogger.error("run syncache db task happened error",e);
		}
	}
}
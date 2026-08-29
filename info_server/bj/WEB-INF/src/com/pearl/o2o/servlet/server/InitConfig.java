package com.pearl.o2o.servlet.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.ServiceLocator;
public class InitConfig extends HttpServlet {
	private static final long serialVersionUID = -3393897598667208451L;
	static Logger log = LoggerFactory.getLogger(InitConfig.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		try {
			log.info("init config in cache start");
			ServiceLocator.createService.initConfigInCache();
		} catch (Exception e) {
			ServiceLocator.nosqlLogger.error("InitConfig happened error",e);
		}
	}
}
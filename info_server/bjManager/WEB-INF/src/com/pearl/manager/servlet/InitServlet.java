package com.pearl.manager.servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.server.LogServer;
import com.pearl.manager.utils.ConfigurationUtil;


public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = -4241967861991330409L;
	static Logger log = LoggerFactory.getLogger(InitServlet.class.getName());

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);	
		try{
//			 BeanFactory context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
//			 ConfigurationUtil.beanFactory = context;
			 
			int xunleiPort = Integer.valueOf(ConfigurationUtil.LOGSERVER_XUNLEI_PORT);
			int logPort = Integer.valueOf(ConfigurationUtil.LOGSERVER_LOG_PORT);
			LogServer xunleiLogServer = new LogServer(xunleiPort);
			xunleiLogServer.startLogServer();
			
			LogServer logServer = new LogServer(logPort);
			logServer.startLogServer();
		}
		catch(Exception e){
			log.error("Error in InitServlet: " , e);
		}	
	}
	@Override
	public void destroy() {
		super.destroy();
		System.exit(0);
	}
}


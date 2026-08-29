package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.pearl.o2o.schedule.RecreatePersonTopTask;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class InitPersonTopServlet extends HttpServlet {
	private static final long serialVersionUID = -9895932093665436L;
	public static Logger logger = ServiceLocator.crtPsnTopLog;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		try {
			logger.info("InitPersonTopServlet/ManualTask:\t" + CommonUtil.simpleDateFormat.format(new Date()));
			RecreatePersonTopTask.isManual=true;
			new RecreatePersonTopTask().run();
		} catch (Exception e) {
			logger.error("InitPersonTopServlet/Error:\t",e);
		}
	}
}

package com.pearl.servlet;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.pearl.service.NosqlService;
import com.pearl.utils.Constants;
import com.pearl.utils.ServiceLocator;
import com.pearl.utils.XunleiLogWriteTask;


public class InitServlet extends HttpServlet{
	private static final long serialVersionUID = 5257448374223567234L;
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServiceLocator.init(config);
		ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new XunleiLogWriteTask(),60,Constants.XUNLEI_LOG_WRITE_TIME, TimeUnit.SECONDS);
	}
	
	private NosqlService nosqlService;
	public NosqlService getNosqlService() {
		return nosqlService;
	}
	public void setNosqlService(NosqlService nosqlService) {
		this.nosqlService = nosqlService;
	}

}

package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.o2o.schedule.CompeteBuyOverTask;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class CompeteBuyTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 7804829841192248296L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		try {
			ServiceLocator.competeBuylog.info("CompeteBuyTaskServlet/ManualTask:\t" + CommonUtil.simpleDateFormat.format(new Date()));
			new CompeteBuyOverTask(true).run();
		} catch (Exception e) {
			ServiceLocator.competeBuylog.error("CompeteBuyTaskServlet/Error:\t",e);
		}
	}

}

package com.pearl.o2o.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.StringUtil;

@SuppressWarnings("serial")
public class StageClear extends BaseServerServlet{
	private static Logger logger = Logger.getLogger(StageClear.class);
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		try{
			int rid = Integer.valueOf(req.getParameter("rid"));
			logger.info("get stage clear result. rid :" + rid);
			String result = mcc.get(CacheUtil.sStageClear(rid));
			if(!StringUtil.isEmptyString(result)){
				res.getWriter().write(result);
			}else {
				throw new Exception("stage clear data not exist. rid: " + rid);
			}
		}catch (Exception e){
			res.getWriter().write("error = \"fail to get stage clear data\"" );
			logger.error("fail to get stage clear. Exception is" + e );
		}
	}
	
}

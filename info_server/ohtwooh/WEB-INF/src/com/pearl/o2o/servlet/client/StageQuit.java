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
public class StageQuit extends BaseServerServlet{
	private static Logger logger = Logger.getLogger(StageQuit.class);
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		try{
			int rid = Integer.valueOf(req.getParameter("rid"));
			int cid = Integer.valueOf(req.getParameter("cid"));
			
			String result = mcc.get(CacheUtil.sStageQuit(rid, cid));

			if(!StringUtil.isEmptyString(result)){
				res.getWriter().write(result);
			}else {
				throw new Exception("stage quit data not exist. rid: " + rid + " cid :" + cid);
			}
		}catch (Exception e){
			res.getWriter().write("error = \"fail to get stage clear data\"" );
			logger.error("fail to get stage clear. Exception is" + e );
		}
	}
	
}

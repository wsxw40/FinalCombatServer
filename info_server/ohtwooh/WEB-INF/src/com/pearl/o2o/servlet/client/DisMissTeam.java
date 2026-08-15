package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class DisMissTeam extends BaseClientServlet {


	private static final long serialVersionUID = -5235644177360535709L;
	static Logger logger=Logger.getLogger(DisMissTeam.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			int teamId=StringUtil.toInt(req.getParameter("tid"));
			deleteService.deleteTeam(userId, playerId, teamId);
			out.write(Converter.commonFeedback(null));
		}
		catch(Exception e){
			logger.error("Error in DisMissTeam: ", e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
}

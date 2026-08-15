package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class UpgradeTeam extends BaseClientServlet {

	private static final long serialVersionUID = -713707000142394514L;
	static Logger  log=Logger.getLogger(UpgradeTeam.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			int teamId=StringUtil.toInt(req.getParameter("tid"));
			int type=StringUtil.toInt(req.getParameter("type"));
			int playerItemId=StringUtil.toInt(req.getParameter("pid"));
			updateService.upgradeTeam(userId, playerId, playerItemId, teamId, type);
			String result = Converter.commonFeedback(null);	
			out.write(result);
		}
		catch(Exception e){
			log.error("Error in UpgradeTeam: ", e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
}

package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.TeamHistory;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetTeamHistoryList extends BaseClientServlet {
	
	private static final long serialVersionUID = 385922639423472952L;
	static Logger logger=Logger.getLogger(GetTeamHistoryList.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int teamId = StringUtil.toInt(req.getParameter("tid"));
			List<TeamHistory> list=getService.getTeamHistoryById(teamId);
			out.write(Converter.teamHistory(list));
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			out.write(Converter.commonFeedback("系统出现异常错误，请联系GM"));
		}
	}

}

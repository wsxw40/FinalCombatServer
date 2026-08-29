
package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author bobby
 */
public class GetTeam extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=Logger.getLogger(GetTeam.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int teamId = StringUtil.toInt(req.getParameter("tid"));
			Team team=getService.getTeam(teamId);
			team.calculateParam();
			out.write(Converter.team(team));
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			out.write(Converter.commonFeedback("系统出现异常错误，请联系GM"));
		}
	}

}

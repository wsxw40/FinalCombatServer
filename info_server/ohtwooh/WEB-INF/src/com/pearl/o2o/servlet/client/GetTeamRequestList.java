
package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author Timon
 */
public class GetTeamRequestList extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=Logger.getLogger(GetTeamRequestList.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int teamId = StringUtil.toInt(req.getParameter("tid"));
			List<PlayerTeam> unApprovedMemberList= getService.getUnapprovedMember(teamId);
			out.write(Converter.teamRequestList(unApprovedMemberList));
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			out.write(Converter.commonFeedback("系统出现异常错误，请联系GM"));
		}
	}
}

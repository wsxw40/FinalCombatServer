
package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author Timon
 */
public class TeamUpdateOp extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=Logger.getLogger(TeamUpdateOp.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int teamId = Integer.valueOf(req.getParameter("tid"));
			String action = req.getParameter("action");
			//int cid = Integer.valueOf(req.getParameter("cid"));
			String value = req.getParameter("value");
			
			updateService.updateTeamInfo(action, value, teamId);
			out.write("error=nil");
		}catch (Exception e) {
			logger.error("team update op fail:" + e.getMessage(),e);
			if (StringUtil.isEmptyString(e.getMessage())) {
				out.write(Converter.error("系统出现异常错误，请联系GM"));
			}else {
				out.write(Converter.error(e.getMessage()));
			}
		}
	}
}
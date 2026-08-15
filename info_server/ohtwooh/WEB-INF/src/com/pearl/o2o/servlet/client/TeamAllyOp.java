
package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.exception.DuplicateAllyException;
import com.pearl.o2o.utils.Converter;

/**
 * @author Timon
 */
public class TeamAllyOp extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=Logger.getLogger(TeamAllyOp.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int teamId = Integer.valueOf(req.getParameter("tid"));
			int allyId = Integer.valueOf(req.getParameter("allyid"));
			
			if (teamId == allyId) {
				out.write(Converter.error("can not add/remove self"));
				return ;
			}
			
			String action = req.getParameter("action");
			
			if ("add".equals(action)) {
				updateService.addAlly(teamId, allyId);
			}else if ("delete".equals(action)) {
				updateService.removeAlly(teamId, allyId);
			}else{
				throw new Exception("unknown action " + action);
			}
			out.write("error=nil");
		}
		catch(DuplicateAllyException de){
			out.write(Converter.error("此战队已经是您的同盟了"));
		}
		catch (Exception e) {
			logger.error("team update op fail:" + e.getMessage(),e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
}
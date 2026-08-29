package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetMyTeamInfo extends BaseClientServlet {


	private static final long serialVersionUID = 964459518992817674L;
	static Logger logger=Logger.getLogger(GetTeam.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			PlayerTeam pt = null;
			Team team=getService.getTeamByPlayerId(playerId);
			if(team!=null){
				team.calculateParam();
				for (PlayerTeam playerTeam : team.getMemberList()) {
					if (playerTeam.getPlayerId().equals(playerId)) {
						pt = playerTeam; 
						break;
					}
				}
			}
			out.write(Converter.myTeam(team, pt));
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			out.write(Converter.commonFeedback("Error happened in GetTeam. Please contact system administrator"));
		}
	}

}

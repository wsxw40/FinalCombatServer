package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class CreateTeamPre extends BaseClientServlet {

	private static final long serialVersionUID = -2849165222177402972L;
	static Logger log=Logger.getLogger(CreateTeamPre.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();	
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			Player player=getService.getPlayerById(playerId);
			Team team=getService.getTeamByPlayerId(playerId);
			if(team!=null){
				throw new BaseException(ExceptionMessage.NOT_CREATE_TEAM);
			}
			if(player.getRank()<Constants.CREATE_TEAM_LIMIT){
				throw new BaseException(CommonUtil.messageFormat(ExceptionMessage.LEVEL_NOT_TEAM, 10));
			}
			int count=getService.countPlayerItemForTeam(userId, playerId, Constants.DEFAULT_ITEM_TYPE, Constants.DEFAULT_SPECIEL_ITEM_SUBTYPE, Constants.DEFAULT_TEAM_IID);
			if(count<1){
				throw new BaseException(ExceptionMessage.NOT_ITEM_TEAM);
			}
			out.write(Converter.commonFeedback(null));
		}catch (BaseException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
			out.write(Converter.commonFeedback(e.getMessage()));
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
			out.write(Converter.commonFeedback("Error happened in CreateTeam. Please contact system administrator"));
		}
	}

}

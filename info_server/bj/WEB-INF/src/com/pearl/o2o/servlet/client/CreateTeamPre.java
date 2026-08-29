package com.pearl.o2o.servlet.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;

public class CreateTeamPre extends BaseClientServlet {

	private static final long serialVersionUID = -2849165222177402972L;
	static Logger log=LoggerFactory.getLogger(CreateTeamPre.class.getName());
	private String[] paramNames={"uid","cid"};
	@Override
	protected String innerService(String... args) {
		try{
//			//int userId = StringUtil.toInt(args[0]);
//			int playerId = StringUtil.toInt(args[1]);
//			Player player=getService.getPlayerById(playerId);
//			Team team=getService.getTeamByPlayerId(playerId);
//			if(player.getRank()<Constants.CREATE_TEAM_LIMIT){
//				BaseException be=new BaseException(CommonUtil.messageFormat(ExceptionMessage.LEVEL_NOT_TEAM, 10));
//				be.data="1";
//				throw be;
//			}
//			int count=getService.countPlayerItemForTeam(playerId, Constants.DEFAULT_ITEM_TYPE, Constants.DEFAULT_SPECIEL_ITEM_SUBTYPE, Constants.DEFAULT_TEAM_IID);
//			if(count<1){
//				BaseException be=new BaseException(ExceptionMessage.NOT_ITEM_TEAM);
//				be.data="2";
//				throw be;
//			}
//			if(team!=null){
//				throw new BaseException(ExceptionMessage.NOT_CREATE_TEAM);
//			}
			return Converter.commonFeedback(null);
		}

		catch (Exception e) {
			return Converter.warn(ExceptionMessage.ERROR_MESSAGE);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}

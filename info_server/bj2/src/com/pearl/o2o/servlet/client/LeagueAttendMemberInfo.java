package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.LeagueMember;
import com.pearl.o2o.pojo.LeagueMemberImpl;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
/**
 * 联赛参加人员表
 * @author zhaolianming
 *
 */
public class LeagueAttendMemberInfo extends BaseClientServlet {
	//rpc.safecall("league_attend_member_info",{pid = 10638000},function(data) end)
	private static final long serialVersionUID = -1633529949445850561L;
	private static Logger log = LoggerFactory.getLogger(LeagueAttendMemberInfo.class.getName());
	private static final String[] paramNames = {"pid"};
	protected String innerService(String... args) {
		try{
		    if (!LeagueApply.isApplyDate()){
                return Converter.error(CommonMsg.NOT_APPLY_TIME);// 不在报名时间段
		    }
			//是否在报名时间段
			//客户端进行一次判断后，服务端在进行判断。
			final int playerId = StringUtil.toInt(args[0]);// 获得玩家id

			Player player = getService.getPlayerById(playerId);
	        Integer teamId = player.getTeamId();
	        Team team = getService.getTeam(player.getTeamId());
	        if (teamId == 0)
	            return Converter.error(CommonMsg.NOT_TEAM_ERROR);
	        if (team == null)
                return Converter.error(CommonMsg.NOT_TEAM_ERROR);
	        if (team.getApply() != 1)
                return Converter.error(CommonMsg.NOT_TEAM_ERROR);
	        
			Map<Integer, ArrayList<LeagueMember>> membersMap = LeagueMemberImpl.getCheckMembersMap(teamId);
            return Converter.LeagueAttendMemberInfo(membersMap);
		}catch(Exception e){
			log.warn("Error in "+LeagueAttendMemberInfo.class.getName()+": " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	protected String[] paramNames() {
		return paramNames;
	}
}
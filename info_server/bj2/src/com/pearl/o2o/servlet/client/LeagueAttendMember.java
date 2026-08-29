package com.pearl.o2o.servlet.client;


import java.util.ArrayList;
import java.util.List;
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
 * 联赛人员参加与退出
 * @author zhaolianming
 *
 */
public class LeagueAttendMember extends BaseClientServlet {
	//rpc.safecall("league_attend_member",{pid = 10638000,gt = 1，state = 0},function(data) end)
	private static final long serialVersionUID = -1633529949445850561L;
	private static Logger log = LoggerFactory.getLogger(LeagueAttendMember.class.getName());
	private static final String[] paramNames = {"pid","gt","state"};
	protected String innerService(String... args) {
		try{
			//是否在报名时间段`
		    if (!LeagueApply.isApplyDate()){
                return Converter.error(CommonMsg.NOT_APPLY_TIME);// 不在报名时间段
            }
			//客户端进行一次判断后，服务端在进行判断。
			final int playerId = StringUtil.toInt(args[0]);		// 获得玩家id
			//0.高级战场(歼灭) 1.中级战场(推车) 2.低级战场(团队竞技) 3.低级战场(占点) 4.低级战场(刀战) 5.特殊战场(跳跳乐)
			final int gameType = StringUtil.toInt(args[1]);		// 参加的游戏模式
			final int state = StringUtil.toInt(args[2]);		// 0为参加，1为退出
			//====数值的常规验证start====
		    Player player = getService.getPlayerById(playerId);
            Integer teamId = player.getTeamId();
            Team team = getService.getTeam(player.getTeamId());
            if (teamId == 0)
                return Converter.error(CommonMsg.NOT_TEAM_ERROR);
            if (team == null)
                return Converter.error(CommonMsg.NOT_TEAM_ERROR);
            if (team.getApply() != 1)
                return Converter.error(CommonMsg.NOT_TEAM_ERROR);
			if (gameType<0||gameType>5)
				return Converter.error(CommonMsg.NOT_GAME_PATTERN);
			if (state<0||state>1)
				return Converter.error(CommonMsg.NOT_GAME_PATTERN);
			//====数值的常规验证end====
			Map<Integer, ArrayList<LeagueMember>> membersMap = LeagueMemberImpl.getCheckMembersMap(teamId);
            if (state == 0) {// 0为参加
                List<LeagueMember> members = membersMap.get(gameType);
                if (members == null || members.size() < 6) {
                    membersMap = LeagueMemberImpl.flush(playerId, gameType, teamId, membersMap);
                }
            } else if (state == 1) {// 1为退出
                membersMap = LeagueMemberImpl.flush(playerId, 5, teamId, membersMap);
            }
            return Converter.LeagueAttendMemberInfo(membersMap);
        } catch (Exception e) {
            log.warn("Error in " + LeagueAttendMember.class.getName() + ": ", e);
            return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
        }
	}
	
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}

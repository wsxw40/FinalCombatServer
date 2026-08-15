package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.LeagueMember;
import com.pearl.o2o.pojo.LeagueMemberImpl;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 联赛人员的替换
 * @author zhaolianming
 */
public class LeagueChangeMember extends BaseClientServlet {
    //rpc.safecall("league_change_member",{pid = 10638000,gt = 1，pids = "10642861,10658628"},function(data) end)
    private static final long serialVersionUID = -1633529949445850561L;
    private static Logger log = LoggerFactory.getLogger(LeagueChangeMember.class.getName());
    private static final String[] paramNames = { "pid", "gt", "pids" };

    protected String innerService(String... args) {
        try{
            //是否在报名时间段
            if (!LeagueApply.isApplyDate()){
                return Converter.error(CommonMsg.NOT_APPLY_TIME);// 不在报名时间段
            }
            //客户端进行一次判断后，服务端在进行判断。
            final int playerId = StringUtil.toInt(args[0]);     // 获得玩家id
            final int gameType = StringUtil.toInt(args[1]);       // 获得战队id
            final String playerIds = args[2];      // 替换后的目标玩家
            boolean flag = playerIds.trim().equals("")? false:true;
            //====数值的常规验证start====
            String[] playerIdArray = playerIds.split(",");
            if (flag && playerIdArray.length > 6) 
                return Converter.error(CommonMsg.ALREADY_MEMBER_BEYOND);
            if (gameType < 0 || gameType > 5)//模式不存在
                return Converter.error(CommonMsg.NOT_GAME_PATTERN);
            Player player = getService.getPlayerById(playerId);
            Integer teamId = player.getTeamId();
            Team team = getService.getTeam(player.getTeamId());
            if (teamId == 0)
                return Converter.error(CommonMsg.NOT_TEAM_ERROR);
            if (team == null)
                return Converter.error(CommonMsg.NOT_TEAM_ERROR);
            if (team.getApply() != 1)
                return Converter.error(CommonMsg.NOT_TEAM_ERROR);
            PlayerTeam playerTeam = getService.getPlayerTeam(teamId, playerId);
            if (playerTeam == null)//你不是该战队成员
                return Converter.error(CommonMsg.NOT_TEAM_MEMBER);
            if (playerTeam.getJob() <= 2)//判断该玩家是否有报名权限
                return Converter.error(CommonMsg.NOT_RIGHTS); // 你的权限不足，不能报名战队赛。
            //====数值的常规验证end====
            Map<Integer, ArrayList<LeagueMember>> membersMap = LeagueMemberImpl.getCheckMembersMap(teamId);
            //获得要改变名单的游戏类型
            ArrayList<LeagueMember> members = membersMap.get(gameType);
            //获得当前游戏类型的玩家名单
            //将要放到游戏类型5的玩家名单
            ArrayList<Integer> nowList = new ArrayList<Integer>();
            if (members!=null) {
                for (LeagueMember member : members) {
                    nowList.add(member.getPlayerId());
                }
            }
            //获得目标玩家名单
            //将要放到当前游戏类型的玩家名单
            ArrayList<Integer> tagList = new ArrayList<Integer>();
            if (flag) {
                for (int i = 0; i < playerIdArray.length; i++) {
                    tagList.add(Integer.valueOf(playerIdArray[i]));
                }
            }
            //排除重复的玩家
            ArrayList<Integer> repetitionList = new ArrayList<Integer>();
            if (nowList.size() != 0 && tagList.size() != 0) {
                for (int now : nowList) {
                    for (int tag : tagList) {
                        if (now == tag) {
                            repetitionList.add(now);
                            break;
                        }
                    }
                }
            }
            nowList.removeAll(repetitionList);
            tagList.removeAll(repetitionList);
            //把tagList丢当前类型里面
            for (Integer playerIdNow : tagList) {
                membersMap = LeagueMemberImpl.flush(playerIdNow, gameType, teamId, membersMap);
                soClient.puchCMDtoClient(getService.getPlayerById(playerIdNow).getName(),CommonUtil.messageFormat(CommonMsg.LEAGUE_CHANGE_MY_MEMBER,gameType));
            }
            //把nowList丢5里面
            for (int playerIdNow : nowList) {
                membersMap = LeagueMemberImpl.flush(playerIdNow, 5, teamId, membersMap);
                soClient.puchCMDtoClient(getService.getPlayerById(playerIdNow).getName(),CommonUtil.messageFormat(CommonMsg.LEAGUE_CHANGE_MY_MEMBER,5));
            }
            return Converter.LeagueAttendMemberInfo(membersMap);
        }catch(Exception e){
            log.warn("Error in "+LeagueChangeMember.class.getName()+": " , e);
            return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
        }
    }

    @Override
    protected String[] paramNames() {
        return paramNames;
    }
}

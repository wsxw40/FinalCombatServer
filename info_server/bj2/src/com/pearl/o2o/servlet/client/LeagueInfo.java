package com.pearl.o2o.servlet.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.LeagueMember;
import com.pearl.o2o.pojo.LeagueMemberImpl;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
/**
 * 联赛基本信息
 * @author zhaolianming
 *
 */
public class LeagueInfo extends BaseClientServlet {
	//rpc.safecall("league_info",{pid = 10638000,type = 0},function(data) end)
	private static final long serialVersionUID = -1633529949445850561L;
	private static Logger log = LoggerFactory.getLogger(LeagueInfo.class.getName());
	private static final String[] paramNames = {"pid","type"};
	protected String innerService(String... args) {
		try{
			final int playerId = StringUtil.toInt(args[0]);// 获得玩家id
			final int type = StringUtil.toInt(args[1]);// 获得type 0为获得报名情况，1为报名
			String apply_start      = ConfigurationUtil.LEAGUE_APPLY_START;
            String apply_end        = ConfigurationUtil.LEAGUE_APPLY_END;
            String game_start       = ConfigurationUtil.LEAGUE_GAME_START;
            String game_end         = ConfigurationUtil.LEAGUE_GAME_END;
            String weeks            = ConfigurationUtil.LEAGUE_WEEKS;
            String remark_1         = ConfigurationUtil.LEAGUE_REMARK_1;
            String remark_2         = ConfigurationUtil.LEAGUE_REMARK_2;
            String teamName = "";
            //difference
            int timeDiff = 0;
            int timeDiffType = 0;//0距报名开始时间，1距报名结束时间，2距比赛开始时间，3距比赛结束时间
            int haveTeam = 0;//0没战队，1有战队
            int job = 0;//0没权限，1有权限
            int apply = 0;//0没报名，1已报名
            int start = 0;//0报名时间段，1空闲，2比赛时间段
            int gameType = 5;//当前玩家的战场类型
            
            int diff_0 = LeagueApply.TimeDifference(ConfigurationUtil.LEAGUE_APPLY_START);
            int diff_1 = LeagueApply.TimeDifference(ConfigurationUtil.LEAGUE_APPLY_END);
            int diff_2 = LeagueApply.TimeDifference(ConfigurationUtil.LEAGUE_GAME_START);
            int diff_3 = LeagueApply.TimeDifference(ConfigurationUtil.LEAGUE_GAME_END);
            if (diff_0 < diff_1 && diff_0 < diff_2&& diff_0 < diff_3) {
                timeDiff = diff_0;
                timeDiffType = 0;
                start = 1;
            }
            if (diff_1 < diff_0 && diff_1 < diff_2&& diff_1 < diff_3) {
                timeDiff = diff_1;
                timeDiffType = 1;
                start = 0;
            }
            if (diff_2 < diff_0 && diff_2 < diff_1&& diff_2 < diff_3) {
                timeDiff = diff_2;
                timeDiffType = 2;
                start = 1;
            }
            if (diff_3 < diff_0 && diff_3 < diff_1&& diff_3 < diff_2) {
                timeDiff = diff_3;
                timeDiffType = 3;
                start = 2;
            }
            
			if (remark_1==null||remark_1.trim().equals("")) {
                remark_1="比赛日:";
                String[] weeksplit = weeks.split(";");
                for (String week : weeksplit) {
                    switch (Integer.valueOf(week)) {
                    case 1:remark_1+="周一 "; break;    
                    case 2:remark_1+="周二 "; break;
                    case 3:remark_1+="周三 "; break;
                    case 4:remark_1+="周四 "; break;
                    case 5:remark_1+="周五 "; break;
                    case 6:remark_1+="周六 "; break;
                    case 7:remark_1+="周日 "; break;
                    default:break;
                    }
                }
            }
            if (remark_2==null||remark_2.trim().equals("")) {
                remark_2+="报名时间:"+apply_start+"-"+apply_end;
                remark_2+="    ";
                remark_2+="比赛时间:"+game_start+"-"+game_end;
            }
            Player player = getService.getPlayerById(playerId);
            Team team = getService.getTeam(player.getTeamId());
            if (team != null) {
                haveTeam = 1;
                PlayerTeam playerTeam = getService.getPlayerTeam(team.getId(), playerId);
                if(team.getApply()==1){
                    LeagueMember lm = LeagueMemberImpl.getPlayerById(player.getTeamId(), playerId);
                    if (lm != null) {
                        gameType = lm.getGameType();
                    }
                }
                if (playerTeam != null && playerTeam.getJob() > 2)//判断该玩家是否有报名权限
                    job = 1;
                if (team != null) {
                    teamName = team.getName();
                    apply = team.getApply();
                }
            }
			return Converter.League(apply_start, apply_end, game_start, game_end,remark_1,remark_2,teamName
			        ,haveTeam,job,apply,start
			        ,timeDiff,timeDiffType,gameType);
		}catch(Exception e){
			log.warn("Error in "+LeagueInfo.class.getName()+": " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
package com.pearl.o2o.servlet.client;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.LeagueMemberImpl;
import com.pearl.o2o.pojo.LeagueTeam;
import com.pearl.o2o.pojo.LeagueTeamImpl;
import com.pearl.o2o.pojo.LeagueTeamTopImpl;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
/**
 * 联赛报名
 * @author zhaolianming
 *
 */
public class LeagueApply extends BaseClientServlet {
	//rpc.safecall("league_apply",{pid = 10638000,type = 0},function(data) end)
	private static final long serialVersionUID = -1633529949445850561L;
	private static Logger log = LoggerFactory.getLogger(LeagueApply.class.getName());
	private static final String[] paramNames = {"pid","type"};
	protected String innerService(String... args) {
		try{
			//是否在报名时间段
			//客户端进行一次判断后，服务端在进行判断。
			if (!isApplyDate())
				return Converter.error(CommonMsg.NOT_APPLY_TIME);// 不在报名时间段
			final int playerId = StringUtil.toInt(args[0]);// 获得玩家id
			final int type = StringUtil.toInt(args[1]);// 获得type 0为获得报名情况，1为报名
			
			Player player = getService.getPlayerById(playerId);
			Integer teamId = player.getTeamId();
			if (teamId == 0)
                return Converter.error(CommonMsg.NOT_TEAM_ERROR);
            Team team = getService.getTeam(player.getTeamId());
            if (team == null)
                return Converter.error(CommonMsg.NOT_TEAM_ERROR);
			PlayerTeam playerTeam = getService.getPlayerTeam(teamId, playerId);
			if (playerTeam == null)//判断是否在该战队成员
				return Converter.error(CommonMsg.NOT_TEAM_MEMBER);
			if (type==0) //获得报名情况
				return Converter.LeagueApply(team.getApply());
			//只有战队的队长和管理员有权限报名，普通成员不能报名。
			if (playerTeam.getJob() <= 2)//判断该玩家是否有报名权限
				return Converter.error(CommonMsg.NOT_RIGHTS); // 你的权限不足，不能报名战队赛。
			if (team.getApply() == 1) {//你的战队已报名参加战队赛。
				return Converter.error(CommonMsg.ALREADY_APPLY);
			} else {// 报名成功
				team.setApply(1);
				//因为有本赛季的概念，所以只清理出战名单。
	            LeagueTeam leagueTeam = new LeagueTeam();
	            leagueTeam.setWin(0);
	            leagueTeam.setFall(0);
	            leagueTeam.setScoce(0);
	            //插入联赛战队积分排行榜
	            LeagueTeamTopImpl.updataLeagueTeamTop(teamId,0);
	            //LeagueTeamTopImpl.insertLeagueTeamTop(teamId,0);
	            //插入联赛战队胜负积分表
	            LeagueTeamImpl.updataTeam(teamId, leagueTeam);
	            //LeagueTeamImpl.insertTeam(teamId, leagueTeam);
	            //把所有玩家存入
	            LeagueMemberImpl.resetTeamById(teamId);
				updateService.updateTeamInfo(team);
				return Converter.LeagueApply(1);
			}
		}catch(Exception e){
			log.warn("Error in "+LeagueApply.class.getName()+": " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	/**
	 * 时间差,单位秒
	 * nowTime<time,time-nowTime
	 * nowTime>time,(24 * 60 * 60) + time-nowTime
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static int TimeDifference(String time) throws ParseException{
	      SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
          Date date = new Date();
          String format = ymd.format(date);
          SimpleDateFormat hhmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm");
          Date start = hhmmss.parse(format + " " + time.trim());
          long a= (start.getTime()-date.getTime())/1000;
          a = a > 0 ? a : (24 * 60 * 60) + a;
          return (int)a;
	}
	
	/**
	 * true在报名时间段 false不在报名时间段
	 * @return
	 */
	public static boolean isApplyDate() {
        return isDate(Constants.LEAGUE_ATTEND_TIME_KEY_PREFIX, ConfigurationUtil.LEAGUE_APPLY_START, ConfigurationUtil.LEAGUE_APPLY_END);
    }
	/**
     * true在比赛时间段 false不在比赛时间段
     * @return
     */
    public static boolean isGameDate() {
        return isDate(Constants.LEAGUE_GAME_TIME_KEY_PREFIX, ConfigurationUtil.LEAGUE_GAME_START, ConfigurationUtil.LEAGUE_GAME_END);
    }
	
    /**
     * 
     * @param key
     * @param start_str
     * @param end_str
     * @return
     */
	private static boolean isDate(String key,String start_str,String end_str){
	    NoSql nosql = ServiceLocator.nosqlService.getNosql();
        try {
            String lat = nosql.get(key);
            if (lat != null && lat.equals("1")) //为1为真，表示可以报名
                return true;
            if (lat != null && lat.equals("0")) //为0为假，表示不可以报名
                return false;
        } catch (Exception e1) {
        }
        boolean flag = false;
        int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String[] split = ConfigurationUtil.LEAGUE_WEEKS.split(";");
        for (int j = 0; j < split.length; j++) {
            if (split[j].equals(week + "")) {
                flag = true;
                break;
            }
        }
        if (flag) {
            try {
                SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String format = ymd.format(date);
                SimpleDateFormat hhmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date start = hhmmss.parse(format + " " + start_str);
                Date end = hhmmss.parse(format + " " + end_str);
                if (date.getTime() >= start.getTime() && date.getTime() <= end.getTime()) {//在报名时间内
                    nosql.set(key, "1");
                    nosql.expireAt(key, end.getTime()/1000);//设置生命周期到结束为止
                    return true;
                } else {
                    nosql.set(key, "0");
                    nosql.expireAt(key, start.getTime()/1000);//设置生命周期到结束为止
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        try {
            nosql.set(key, "0");
            nosql.expire(key, 60*60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
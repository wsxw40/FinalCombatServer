package com.pearl.o2o.servlet.client;

import java.text.ParseException;
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
 */
public class LeagueApply extends BaseClientServlet {
	//rpc.safecall("league_apply",{pid = 10638000,type = 0},function(data) end)
	private static final long serialVersionUID = -1633529949445850561L;
	private static Logger log = LoggerFactory.getLogger(LeagueApply.class.getName());
	private static final String[] paramNames = { "pid", "type" };

	@Override
	protected String innerService(String... args) {
		try {
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
			if (type == 0) //获得报名情况
				return Converter.LeagueApply(team.getApply());
			//只有战队的队长和管理员有权限报名，普通成员不能报名。
			if (playerTeam.getJob() <= 2)//判断该玩家是否有报名权限
				return Converter.error(CommonMsg.NOT_RIGHTS); // 你的权限不足，不能报名战队赛。
			if (team.getApply() == 1) {//你的战队已报名参加战队赛。
				return Converter.error(CommonMsg.ALREADY_APPLY);
			} else {// 报名成功
				team.setApply(1);
				team.setLeagueFail(team.getLeagueFail() == null ? 0 : team.getLeagueFail());
				team.setLeagueWin(team.getLeagueWin() == null ? 0 : team.getLeagueWin());
				team.setLeagueScore(team.getLeagueScore() == null ? 0 : team.getLeagueScore());
				//因为有本赛季的概念，所以只清理出战名单。
				LeagueTeam leagueTeam = null;
				try {
					leagueTeam = LeagueTeamImpl.getTeamById(teamId, 1);
				} catch (Exception e) {
					leagueTeam = new LeagueTeam();
					leagueTeam.setWin(0);
					leagueTeam.setFall(0);
					leagueTeam.setScoce(0);
					//插入联赛战队积分排行榜
					LeagueTeamTopImpl.updataLeagueTeamTop(teamId, 0);
					LeagueTeamTopImpl.updataDailyScoreTop(teamId, 0);
					//LeagueTeamTopImpl.insertLeagueTeamTop(teamId,0);
					//插入联赛战队胜负积分表
					LeagueTeamImpl.updataTeam(teamId, leagueTeam);
					//LeagueTeamImpl.insertTeam(teamId, leagueTeam);
				}
				//把所有玩家存入
				LeagueMemberImpl.resetTeamById(teamId);
				updateService.updateTeamInfo(team);
				return Converter.LeagueApply(1);
			}
		} catch (Exception e) {
			log.warn("Error in " + LeagueApply.class.getName() + ": ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	/**
	 * 时间差,单位秒 nowTime<time,time-nowTime nowTime>time,(24 * 60 * 60) + time-nowTime
	 * @param time
	 * @return
	 */
	public static int TimeDifference(String time) {
		Date useTime = LeagueApply.parseTimeHHMM(time).getTime();
		long a = (useTime.getTime() - new Date().getTime()) / 1000;
		return (int) (a = a > 0 ? a : (24 * 60 * 60) + a);
	}

	/**
	 * 时间差,单位秒 nowTime<time,time-nowTime nowTime>time,(24 * 60 * 60) + time-nowTime
	 * @param time
	 * @return
	 */
	public static int TimeDifference_2(String time) {
		Date useTime = LeagueApply.parseTimeHHMM(time).getTime();
		long a = (useTime.getTime() - new Date().getTime()) / 1000;
		return (int) a;
	}

	/**
	 * 解析"hh:mm"的字符串为当天的该时间
	 * @param time
	 * @return
	 */
	public static Calendar parseTimeHHMM(String time) {
		String[] split = time.split(":");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(split[0]));
		calendar.set(Calendar.MINUTE, Integer.valueOf(split[1]));
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	/**
	 * 添加时间
	 * @param time
	 * @param minute 分位
	 * @return
	 * @throws ParseException
	 */
	public static Calendar timeAddMM(String time, int minute) throws ParseException {
		Calendar calendar = LeagueApply.parseTimeHHMM(time);
		calendar.add(Calendar.MINUTE, minute);
		return calendar;
	}

	/**
	 * true在报名时间段 false不在报名时间段
	 * @return
	 */
	public static boolean isApplyDate() {
		Date startTime = LeagueApply.parseTimeHHMM(ConfigurationUtil.LEAGUE_APPLY_START).getTime();
		Date endTime = LeagueApply.parseTimeHHMM(ConfigurationUtil.LEAGUE_APPLY_END).getTime();
		return isDate(Constants.LEAGUE_ATTEND_TIME_KEY_PREFIX, startTime, endTime);
	}

	/**
	 * 0 false在参赛名单不可修改时间段 1 true在参赛名单可修改时间段
	 * @return
	 */
	public static boolean isLeagueMemberDate() {
		Date startTime = LeagueApply.parseTimeHHMM(ConfigurationUtil.LEAGUE_APPLY_END).getTime();
		Calendar endCalendar = LeagueApply.parseTimeHHMM(ConfigurationUtil.LEAGUE_GAME_START);
		endCalendar.add(Calendar.MINUTE, -1);
		Date endTime = endCalendar.getTime();
		return isDate(Constants.LEAGUE_MEMBER_TIME_KEY_PREFIX, startTime, endTime);
	}

	/**
	 * true在比赛时间段 false不在比赛时间段
	 * @return
	 */
	public static boolean isGameDate() {
		Date startTime = LeagueApply.parseTimeHHMM(ConfigurationUtil.LEAGUE_GAME_START).getTime();
		Date endTime = LeagueApply.parseTimeHHMM(ConfigurationUtil.LEAGUE_GAME_END).getTime();
		return isDate(Constants.LEAGUE_GAME_TIME_KEY_PREFIX, startTime, endTime);
	}

	/**
	 * @param key
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private static boolean isDate(String key, Date startTime, Date endTime) {
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
		//查看当前时间在 可比赛日期星期数 内
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
				Date date = new Date();
				if (date.getTime() >= startTime.getTime() && date.getTime() <= endTime.getTime()) {//在报名时间内
					nosql.set(key, "1");
					nosql.expireAt(key, endTime.getTime() / 1000);//设置生命周期到结束为止
					return true;
				} else {
					nosql.set(key, "0");
					nosql.expireAt(key, startTime.getTime() / 1000);//设置生命周期到结束为止
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}
		try {
			nosql.set(key, "0");
			nosql.expire(key, 60 * 60);
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
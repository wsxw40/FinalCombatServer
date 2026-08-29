package com.pearl.o2o.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.LeagueMemberImpl;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;

/**
 * 联赛公共方法
 * @author zhaolianming
 */
public class LeagueUtil {
	private static NoSql noSql = ServiceLocator.nosqlService.getNosql();

	//#0表示正常比赛，1表示训练赛
	public static boolean isLeagueOfficialGame() {
		return "0".equalsIgnoreCase(ConfigurationUtil.LEAGUE_TYPE);
	}

	/**
	 * 发送更变
	 * @param team
	 * @throws Exception
	 */
	public static void lgChangeSend(Team team) throws Exception {
		//String lAMember = Converter.LeagueAttendMemberInfo(LeagueMemberImpl.getCheckMembersMap(team.getId()));
		String uuid = UUID.randomUUID().toString();
		//noSql.set("lAMember" + uuid, lAMember);
		//noSql.expire("lAMember" + uuid, 3);
		List<PlayerTeam> memberList = team.getMemberList();
		if (memberList == null) {
			memberList = ServiceLocator.getService.getPlayerTeamByTeamId(team.getId());
		}
		if (memberList == null) {
			return;
		}
		for (PlayerTeam playerIdNowJob : memberList) {
			String name = ServiceLocator.getService.getPlayerById(playerIdNowJob.getPlayerId()).getName();
			ServiceLocator.soClient.puchCMDtoClient(name, CommonUtil.messageFormat(CommonMsg.LEAGUE_MEMBER_TEMP, uuid));
		}
	}

	/*
	 *发送物品
	 */
	public static void daySend() throws Exception {
		//获得前一天的日期
		Calendar calendar = Calendar.getInstance();
		String flag = noSql.get("LG_DAY_SEND");
		String format_now = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		if (format_now.equalsIgnoreCase(flag)) {
			return;
		}
		noSql.set("LG_DAY_SEND", format_now);

		calendar.add(Calendar.DAY_OF_MONTH, -1);
		String format = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

		Set<String> keys = noSql.keys("LGR*");//获得所有
		//获得所有战斗记录的积分累积，最后用来发送
		HashMap<Integer, Integer> scoceMap = new HashMap<Integer, Integer>();
		for (String key : keys) {
			Map<String, String> lgrs = noSql.hgetAll(key);//LGR7075
			Integer myTeamId = Integer.valueOf(key.substring(3, key.length()));
			HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
			Integer newestTeamId = 0;
			for (Entry<String, String> lgr : lgrs.entrySet()) {
				String key_now = lgr.getKey();
				String value_now = lgr.getValue();

				String[] keyArray = key_now.split("\\|");
				String time = keyArray[0];
				if (!time.endsWith(format))
					continue;//查看是否是今天
				newestTeamId = Integer.valueOf(keyArray[1]);
				String[] valueArray = value_now.split("\\|");
				int myTeamScoce = Integer.valueOf(valueArray[0]);
				if (myTeamScoce == 0)
					continue;
				Integer scoce = hashMap.get(newestTeamId);
				hashMap.put(newestTeamId, (scoce == null ? 0 : scoce) + myTeamScoce);
			}
			Integer scoce = hashMap.get(newestTeamId);
			if (scoce == null)
				continue;
			scoceMap.put(myTeamId, scoce);
		}
		//发送
		int[] sysItemIds = new int[] { 6314, 6283, 6284, 6285, 6286 };
		String[] sysItems = new String[sysItemIds.length];
		for (int i = 0; i < sysItems.length; i++) {
			sysItems[i] = "<C278^2^<SN" + sysItemIds[i] + "^0>^" + CommonUtil.dateFormat.format(new Date()) + ">";
		}
		Payment payment = new Payment(1, 1);
		for (Entry<Integer, Integer> entry : scoceMap.entrySet()) {
			Integer teamId = entry.getKey();
			Integer scoce = entry.getValue();
			int index = 0;
			if (scoce < 10) {
				continue;
			} else if (scoce <= 60) {
				index = 0;
			} else if (scoce <= 120) {
				index = 1;
			} else if (scoce <= 220) {
				index = 2;
			} else if (scoce <= 320) {
				index = 3;
			} else {
				index = 4;
			}
			/*   System.out.println(teamId+" "+scoce+" "+index);
			   List<PlayerTeam> playerTeamByTeamIdSimple = ServiceLocator.getService.getPlayerTeamByTeamIdSimple(teamId);
			   for (PlayerTeam playerTeam : playerTeamByTeamIdSimple) {
			       Player player = ServiceLocator.getService.getPlayerById(playerTeam.getPlayerId());
			       ServiceLocator.createService.sendSystemMail(player, "<C772^0>", sysItems[index], sysItemIds[index], payment);
			   }*/
		}
	}

	/**
	 * 联赛记录清理
	 * @return
	 * @throws Exception
	 */
	public static void leagueRecordClean() throws Exception {
		//发送
		daySend();
		//LAMIS,LAMI,LAT,LGT,LTM`
		//清理联赛参加人员名单
		//战队改成未报名状态
		Set<String> teamIds = noSql.keys(Constants.LEAGUE_ATTEND_MEMBER_INFO_KEY_PREFIX + "*");
		for (String teamIdStr : teamIds) {
			int teamId = Integer.valueOf(teamIdStr.substring(4));
			LeagueMemberImpl.delTeamById(teamId);
			Team team = ServiceLocator.getService.getTeam(teamId);
			if (team != null) {
				team.setApply(0);
				ServiceLocator.updateService.updateTeamInfo(team);
			}

		}
		//联赛参加人员名单的keys总表
		noSql.delete(Constants.LEAGUE_ATTEND_MEMBER_INFO_LIST_KEY_PREFIX);
		noSql.delete(Constants.LEAGUE_ATTEND_TIME_KEY_PREFIX);
		//参赛名单是否可修改
		noSql.delete(Constants.LEAGUE_GAME_TIME_KEY_PREFIX);
		//是否在比赛时间
		noSql.delete(Constants.LEAGUE_MEMBER_TIME_KEY_PREFIX);
		noSql.delete(Constants.LEAGUE_TEAM_MATE_KEY_PREFIX);
		noSql.delete(Constants.LEAGUE_TEAM_MATE_BYE_KEY_PREFIX);
	}

	/**
	 * 联赛赛季清理
	 * @return
	 * @throws Exception
	 */
	public static void leagueSeasonClean() throws Exception {
		//LAMIS,LAMI,LAT,LGT,LTM
		leagueRecordClean();
		//LATIS,LATI,LGRS,LGR
		//删除联赛上个赛季数据
		noSql.delete(Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_OLD_KEY_PREFIX);
		noSql.delete(Constants.LEAGUE_ATTEND_TEAM_INFO_OLD_KEY_PREFIX);
		//联赛积分榜转到上个赛季
		leagueTeamTopToOld();

		//删除联赛战队比赛记录
		Set<String> teamIds = noSql.keys(Constants.LEAGUE_GAME_RECORD_KEY_PREFIX + "*");
		for (String idStr : teamIds) {
			Team team = ServiceLocator.getService.getTeam(Integer.valueOf(idStr.substring(3)));
			if (null != team) {
				team.setLeagueScore(null);
				team.setLeagueWin(null);
				team.setLeagueFail(null);
				team.setFightRecord(null);
				ServiceLocator.updateService.updateTeamInfo(team);
			}
		}
		if (teamIds.size() > 0)
			noSql.delete(teamIds);

	}

	/**
	 * 游戏类型转联赛游戏类型
	 * @param gameTpye
	 * @return
	 */
	public static int leagueGameTypeTogameType(int gameTpye) {
		switch (gameTpye) {
		case 0://高级战场（歼灭）
			return 4;
		case 1://中级战场（推车）
			return 2;
		case 2://低级战场（团队竞技）
			return 0;
		case 3://低级战场（占点）
			return 1;
		case 4://低级战场（刀战）
			return 6;
		case 5://特殊战场（跳跳乐）
			return 17;
		}
		return 0;
	}

	/**
	 * 游戏类型转联赛游戏类型
	 * @param gameTpye
	 * @return
	 */
	public static int gameTypeToleagueGameType(int gameTpye) {
		switch (gameTpye) {
		case 4://高级战场（歼灭）
			return 0;
		case 2://中级战场（推车）
			return 1;
		case 0://低级战场（团队竞技）
			return 2;
		case 1://低级战场（占点）
			return 3;
		case 6://低级战场（刀战）
			return 4;
		case 17://特殊战场（跳跳乐）
			return 5;
		}
		return -1;
	}

	/**
	 * 获得联赛积分
	 * @param bout 胜利盘数差
	 * @param lGameType 联赛战场类型
	 * @return
	 */
	public static int leagueGameScore(int bout, int lGameType) {
		switch (lGameType) {
		case 0://高级战场（歼灭）
			return leagueGameScoreAdvanced(bout);
		case 1://中级战场（推车）
			return leagueGameScoreMiddle(bout);
		case 2://低级战场（团队竞技）
			return leagueGameScoreLow(bout);
		case 3://低级战场（占点）
			return leagueGameScoreLow(bout);
		case 4://低级战场（刀战）
			return leagueGameScoreLow(bout);
		case 5://特殊战场（跳跳乐）
			return 3;
		}
		return 0;

	}

	/**
	 * 联赛高级战场结算
	 * @param Score
	 * @return
	 */
	private static int leagueGameScoreAdvanced(int bout) {
		switch (bout) {
		case 3:
			return 120;
		case 2:
			return 96;
		case 1:
			return 72;
		case -1:
			return 48;
		case -2:
			return 24;
		case -3:
			return 6;
		}
		return 0;
	}

	/**
	 * 联赛中级战场结算
	 * @param Score
	 * @return
	 */
	private static int leagueGameScoreMiddle(int bout) {
		switch (bout) {
		case 2:
			return 70;
		case 1:
			return 40;
		case -1:
			return 15;
		case -2:
			return 5;
		}
		return 0;
	}

	/**
	 * 联赛低级战场结算
	 * @param Score
	 * @return
	 */
	private static int leagueGameScoreLow(int bout) {
		if (bout > 0) {
			return 40;
		} else if (bout < 0) {
			return 10;
		}
		return 0;
	}

	/**
	 * 联赛轮空战场结算
	 * @param Score
	 * @return
	 */
	public static int byeScore(int gameType) {
		switch (gameType) {
		case 0:
			return 120;
		case 1:
			return 70;
		case 2:
			return 40;
		case 3:
			return 40;
		case 4:
			return 40;
		}
		return 0;
	}

	/**
	 * 把本赛季的数据放入上赛季
	 * @throws Exception
	 */
	public static void leagueTeamTopToOld() throws Exception {
		/*		List<LeagueTeam> leagueTeams = LeagueTeamTopImpl.leagueTeamTopPage(0, -1);
				int rankIndex = 1;
				noSql.delete(Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_OLD_KEY_PREFIX);
				noSql.delete(Constants.LEAGUE_ATTEND_TEAM_INFO_OLD_KEY_PREFIX);
				for (LeagueTeam leagueTeam : leagueTeams) {
					noSql.zAdd(Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_OLD_KEY_PREFIX, rankIndex, LeagueTeamTopImpl.joint(leagueTeam, rankIndex));
					noSql.hashSet(Constants.LEAGUE_ATTEND_TEAM_INFO_OLD_KEY_PREFIX, leagueTeam.getTeamId() + "", leagueTeam.getWin() + "|" + leagueTeam.getFall());
					rankIndex++;
				}*/
		noSql.rename(Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX, Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_OLD_KEY_PREFIX);
		noSql.rename(Constants.LEAGUE_ATTEND_TEAM_INFO_KEY_PREFIX, Constants.LEAGUE_ATTEND_TEAM_INFO_OLD_KEY_PREFIX);
	}

}
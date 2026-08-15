package com.pearl.o2o.service.flexservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.LeagueMember;
import com.pearl.o2o.pojo.LeagueMemberImpl;
import com.pearl.o2o.pojo.LeagueTeam;
import com.pearl.o2o.pojo.LeagueTeamImpl;
import com.pearl.o2o.pojo.LeagueTeamTopImpl;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.service.CreateService;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DateFormatUtil;
import com.pearl.o2o.utils.LeagueUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class FlexLeagueInfo {
	private static Logger logger = LoggerFactory.getLogger(FlexLeagueInfo.class);

	/**
	 * 联赛排行榜
	 * @return
	 * @throws Exception
	 */
	public List<String> getUserList() throws Exception {
		List<String> entryList = new ArrayList<String>();
		List<LeagueTeam> leagueTeams = LeagueTeamTopImpl.leagueTeamTopPage(0, -1);
		int rankIndex = 1;
		entryList.add(CommonUtil.simpleDateFormat.format(new Date()) + " 排行榜共" + leagueTeams.size() + "条");
		entryList.add("排名，图标，战队名称，胜率，胜负场次，等级，战斗力，人数，积分，创建时间,战队ID");
		for (LeagueTeam leagueTeam : leagueTeams) {
			entryList.add(LeagueTeamTopImpl.joint(leagueTeam, rankIndex));
			rankIndex++;
		}
		return entryList;
	}

	/**
	 * 查询操作记录
	 * @return
	 * @throws Exception
	 */
	public List<String> getSendOrDelTime() throws Exception {
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		List<String> list = new ArrayList<String>();
		Map<String, String> keys = nosql.hgetAll("lg:sendOrDel:");
		list.add(DateFormatUtil.getYMDHMSSf().format(new Date()) + "刷新时间");
		for (Entry<String, String> kv : keys.entrySet()) {
			list.add(kv.getKey() + "  " + kv.getValue());
		}
		Collections.sort(list);
		Collections.reverse(list);
		return list;
	}

	/**
	 * 根据id获得该战队积分
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public List<String> getTeamIdByInfo(Integer teamId) throws Exception {
		List<LeagueMember> members = LeagueMemberImpl.getMembers(teamId);
		List<String> entryList = new ArrayList<String>();
		LeagueTeam leagueTeam = LeagueTeamImpl.getTeamById(teamId, 1);

		entryList.add(DateFormatUtil.getYMDHMSSf().format(new Date()) + "刷新时间");
		entryList.add("排名，图标，战队名称，胜率，胜负场次，等级，战斗力，人数，积分，创建时间,战队ID");
		entryList.add(LeagueTeamTopImpl.joint(leagueTeam, leagueTeam.getIndex()));

		entryList.add("战场类型，战场积分，战场名字");
		if (members.size() != 0) {
			for (LeagueMember leagueMember : members) {
				entryList.add("\"" + leagueMember.getGameType() + "\",\"" + leagueMember.getScore() + "\",\"" + leagueMember.getName() + "\"");
			}
		}
		return entryList;
	}

	/**
	 * 根据id重置该战队联赛战绩
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public String delTeamById(Integer teamId) {
		try {
			//先删除对应的LAMI
			LeagueMemberImpl.delTeamById(teamId);
			//在删除LATIS里的对应积分
			LeagueTeamTopImpl.delTeamById(teamId);
			//最后把报名状态还原
			Team team = ServiceLocator.getService.getTeam(teamId);
			if (team != null) {
				team.setApply(0);
				team.setLeagueScore(null);
				team.setLeagueWin(null);
				team.setLeagueFail(null);
				team.setFightRecord(null);
				ServiceLocator.updateService.updateTeamInfo(team);
			}
			return "重置该战队联赛战绩:成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "重置该战队联赛战绩:失败";
		}
	}

	/**
	 * 联赛赛季清理
	 * @param date1
	 * @param date2
	 * @return
	 */
	public String close_account(String date1, String date2) {
		String dateFormat = CommonUtil.dateFormat.format(new Date());
		String dateFormatDate = CommonUtil.dateFormatDate.format(new Date());
		if (dateFormat.equals(date1) && dateFormatDate.equals(date2)) {
			try {
				NoSql nosql = ServiceLocator.nosqlService.getNosql();
				if (!nosql.exists(Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX)) {
					record("联赛赛季:本赛季还没有数据！");
				} else {
					LeagueUtil.leagueSeasonClean();
					record("联赛赛季:清理成功");
				}
			} catch (Exception e) {
				logger.error(CommonUtil.simpleDateFormat.format(new Date()) + "del error");
				record("联赛赛季:清理失败");
			}
		} else {
			record("联赛赛季清理:验证码错误");
		}
		return "aaaaaaa";
	}

	/**
	 * 记录操作到redis
	 * @param txt
	 */
	private void record(String txt) {
		try {
			ServiceLocator.nosqlService.getNosql().hashSet("lg:sendOrDel:", DateFormatUtil.getYMDHMSSf().format(new Date()), txt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送物品
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public String sendSysItem(String date1, String date2) {

		if (!LeagueUtil.isLeagueOfficialGame()) {
			record("训练赛无法发送奖品" + CommonUtil.simpleDateFormat.format(new Date()));
			return null;
		}

		String dateFormat = CommonUtil.dateFormat.format(new Date());
		String dateFormatDate = CommonUtil.dateFormatDate.format(new Date());

		NoSql noSql = ServiceLocator.nosqlService.getNosql();
		CreateService createService = ServiceLocator.createService;
		GetService getService = ServiceLocator.getService;

		if (dateFormat.equals(date1) && dateFormatDate.equals(date2)) {
			try {
				String key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
				Set<String> temaIdS = noSql.revRangeSortedSet(key, 0, 16);
				ArrayList<Integer> list = new ArrayList<Integer>();
				for (String str : temaIdS) {
					list.add(Integer.valueOf(str));
				}
				Payment payment = new Payment(1, 1);
				String item_6314 = "<C278^2^<SN6314^0>^" + CommonUtil.dateFormat.format(new Date()) + ">";
				String item_6283 = "<C278^2^<SN6283^0>^" + CommonUtil.dateFormat.format(new Date()) + ">";
				String item_6284 = "<C278^2^<SN6284^0>^" + CommonUtil.dateFormat.format(new Date()) + ">";
				String item_6285 = "<C278^2^<SN6285^0>^" + CommonUtil.dateFormat.format(new Date()) + ">";
				String item_6286 = "<C278^2^<SN6286^0>^" + CommonUtil.dateFormat.format(new Date()) + ">";
				//排名奖励分为冠军、亚军、季军、第4-8名、第9-16名五个档次
				int size = list.size();
				int index = 1;
				if (size >= 1) {
					String str = null;
					for (Integer teamId : list.subList(0, 1)) {
						send(teamId, item_6314, 6314, payment, index++, noSql, createService, getService);
						str += teamId + ",";
					}
					record(str == null ? "联赛冠军奖品:发送失败" : "联赛冠军奖品:发送成功" + str);
				}
				if (size >= 2) {
					String str = null;
					for (Integer teamId : list.subList(1, 2)) {
						send(teamId, item_6283, 6283, payment, index++, noSql, createService, getService);
						str += teamId + ",";
					}
					record(str == null ? "联赛亚军奖品:发送失败" : "联赛亚军奖品:发送成功" + str);
				}
				if (size >= 3) {
					String str = null;
					for (Integer teamId : list.subList(2, 3)) {
						send(teamId, item_6284, 6284, payment, index++, noSql, createService, getService);
						str += teamId + ",";
					}
					record(str == null ? "联赛季军奖品:发送失败" : "联赛季军奖品:发送成功" + str);
				}
				if (size >= 4) {
					String str = null;
					for (Integer teamId : list.subList(3, size >= 8 ? 8 : size)) {
						send(teamId, item_6285, 6285, payment, index++, noSql, createService, getService);
						str += teamId + ",";
					}
					record(str == null ? "联赛第4-8名奖品:发送失败" : "联赛第4-8名奖品:发送成功" + str);
				}
				if (size >= 9) {
					String str = null;
					for (Integer teamId : list.subList(8, size >= 15 ? 15 : size)) {
						send(teamId, item_6286, 6286, payment, index++, noSql, createService, getService);
						str += teamId + ",";
					}
					record(str == null ? "联赛第第9-16名奖品:发送失败" : "联赛第9-16名奖品:发送成功" + str);
				}
				return "联赛奖品:发送成功";
			} catch (Exception e) {
				logger.error(CommonUtil.simpleDateFormat.format(new Date()) + "send error");
				return "联赛奖品:发送失败";
			}
		} else {
			return "验证码输入错误";
		}
	}

	/**
	 * 发送物品
	 * @param teamId
	 * @param sysItem
	 * @param sysItemId
	 * @param payment
	 * @param index
	 * @param noSql
	 * @param createService
	 * @param getService
	 * @throws Exception
	 */
	private void send(Integer teamId, String sysItem, int sysItemId, Payment payment, int index, NoSql noSql, CreateService createService, GetService getService) throws Exception {
		Map<String, String> hgetAll = noSql.hgetAll(Constants.LEAGUE_ATTEND_MEMBER_INFO_KEY_PREFIX + teamId);
		for (String str : hgetAll.keySet()) {
			Integer playerId = Integer.valueOf(str);
			Player player = getService.getPlayerById(playerId);
			log_1(player, teamId, teamId, index);
			createService.sendSystemMail(player, "<C773^0>", sysItem, sysItemId, payment);
		}
	}

	/**
	 * 日志记录
	 * @param player
	 * @param sum
	 * @param sysItemId
	 * @param rankNum
	 */
	private static void log_1(Player player, int sysItemId, int teamId, int index) {
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			ServiceLocator.nosqlService.addXunleiLog("24.1" + Constants.XUNLEI_LOG_DELIMITER + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER + player.getId() + Constants.XUNLEI_LOG_DELIMITER
					+ player.getName() + Constants.XUNLEI_LOG_DELIMITER + sysItemId//玩家拿到的物品
					+ Constants.XUNLEI_LOG_DELIMITER + teamId//玩家战队id
					+ Constants.XUNLEI_LOG_DELIMITER + index//玩家战队排名
					+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())//结算时间
			);
		}
	}
}

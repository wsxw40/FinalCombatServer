package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.LeagueGame;
import com.pearl.o2o.pojo.LeagueGameRecordImpl;
import com.pearl.o2o.pojo.LeagueMemberImpl;
import com.pearl.o2o.pojo.LeagueTeam;
import com.pearl.o2o.pojo.LeagueTeamImpl;
import com.pearl.o2o.pojo.LeagueTeamTopImpl;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.schedule.LeagueGameNotice;
import com.pearl.o2o.servlet.client.LeagueApply;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.LeagueUtil;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 联赛数据
 * @author zhaolianming
 */
public class LeagueGameInfo extends BaseServerServlet {
	private static final long serialVersionUID = 5089293981299897027L;
	static Logger log = LoggerFactory.getLogger(LeagueGameInfo.class.getName());

	@Override
	protected byte[] innerService(BinaryReader r) throws IOException, Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			//分三个主要节点
			//报名结束                          选出一只轮空战队,选出配对战队
			//比赛开始前一分钟，锁出战管理       剩余战队的战场轮空处理，同时发送所有配对数据
			//比赛开始

			//按类型分组的随机配对
			NoSql nosql = ServiceLocator.nosqlService.getNosql();
			//是否在报名时间
			nosql.delete(Constants.LEAGUE_ATTEND_TIME_KEY_PREFIX);
			//是否在比赛时间
			nosql.delete(Constants.LEAGUE_GAME_TIME_KEY_PREFIX);
			//参赛名单是否可修改
			nosql.delete(Constants.LEAGUE_MEMBER_TIME_KEY_PREFIX);
			//获得所有报名战队
			List<Team> teamsByApply = ServiceLocator.getService.getTeamsByApply(1);
			List<LeagueGame> lGameTotal = new ArrayList<LeagueGame>();
			//报名结束时间
			int lApplyEndTime = LeagueApply.TimeDifference(ConfigurationUtil.LEAGUE_APPLY_END);
			//比赛开始时间
			int lGameStartTime = LeagueApply.TimeDifference(ConfigurationUtil.LEAGUE_GAME_START);
			//报名结束时间
			int lApplyEndTime_2 = LeagueApply.TimeDifference_2(ConfigurationUtil.LEAGUE_APPLY_END);
			//比赛开始时间
			int lGameStartTime_2 = LeagueApply.TimeDifference_2(ConfigurationUtil.LEAGUE_GAME_START);

			int nNextTime = lApplyEndTime;
			if (lApplyEndTime_2 > 0) {
				//                out.write(BinaryUtil.toByta(lApplyEndTime));//下一次请求为报名结束
				nNextTime = lGameStartTime_2 - 70;
			}
			if (lApplyEndTime_2 <= 0 && lGameStartTime_2 > 60) {//报名结束时间
				//选出一只轮空战队,选出配对战队,都存入对应数据
				teamByeOrGroup(teamsByApply);
				//                out.write(BinaryUtil.toByta(lGameStartTime_2-60));//下一次请求为报名结束,锁名单
				nNextTime = lGameStartTime_2 - 50;
			} else if (lGameStartTime_2 > 0 && lGameStartTime_2 <= 60 && lApplyEndTime_2 <= 0) {//锁名单
				//选出一只轮空战队,选出配对战队,都存入对应数据
				//根据轮空表处理 剩余战队的战场轮空,同时发送所有配对数据
				lGameTotal = teamGameGroup();
				//                out.write(BinaryUtil.toByta(lApplyEndTime));//下一次请求为报名结束
				nNextTime = lApplyEndTime + 10;
			} else if (lApplyEndTime_2 <= 0 && lGameStartTime > 60) {//距离开始时间小于下次一请求，这样只触发一次
				//teamByeOrGroup(teamsByApply);
				//公告系统
				LeagueGameNotice lgNotice = new LeagueGameNotice(lGameStartTime);
				//				out.write(BinaryUtil.toByta(lApplyEndTime));//下一次请求为报名结束
				nNextTime = lApplyEndTime + 10;
				ServiceLocator.scheduledExecutorService.schedule(lgNotice, lgNotice.scheduledTime(), TimeUnit.SECONDS);
			}

			//teamByeOrGroup(teamsByApply);
			out.write(BinaryUtil.toByta(nNextTime));
			out.write(BinaryUtil.toByta(lGameStartTime));//距离开始时间,单位秒

			out.write(BinaryUtil.toByta(6));//总条数目
			out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(0)));//游戏类型地图 case 4://高级战场（歼灭）
			out.write(BinaryUtil.toByta(6));//地图张数
			out.write(BinaryUtil.toByta(47));//地图id
			out.write(BinaryUtil.toByta(48));//地图id
			out.write(BinaryUtil.toByta(68));//地图id
			out.write(BinaryUtil.toByta(76));//地图id
			out.write(BinaryUtil.toByta(111));//地图id
			out.write(BinaryUtil.toByta(278));//地图id
			out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(1)));//游戏类型地图 case 2://中级战场（推车）
			out.write(BinaryUtil.toByta(3));//地图张数
			out.write(BinaryUtil.toByta(36));//地图id
			out.write(BinaryUtil.toByta(39));//地图id
			out.write(BinaryUtil.toByta(146));//地图id
			out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(2)));//游戏类型地图 case 0://低级战场（团队竞技）
			out.write(BinaryUtil.toByta(5));//地图张数
			out.write(BinaryUtil.toByta(28));//地图id
			out.write(BinaryUtil.toByta(30));//地图id
			out.write(BinaryUtil.toByta(51));//地图id
			out.write(BinaryUtil.toByta(74));//地图id
			out.write(BinaryUtil.toByta(117));//地图id
			out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(3)));//游戏类型地图 case 1://低级战场（占点）
			out.write(BinaryUtil.toByta(3));//地图张数
			out.write(BinaryUtil.toByta(29));//地图id
			out.write(BinaryUtil.toByta(38));//地图id
			out.write(BinaryUtil.toByta(81));//地图id
			out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(4)));//游戏类型地图 case 6://低级战场（刀战）
			out.write(BinaryUtil.toByta(3));//地图张数
			out.write(BinaryUtil.toByta(55));//地图id
			out.write(BinaryUtil.toByta(120));//地图id
			out.write(BinaryUtil.toByta(205));//地图id
			out.write(BinaryUtil.toByta(LeagueUtil.leagueGameTypeTogameType(5)));//游戏类型地图 case 17://特殊战场（跳跳乐）
			out.write(BinaryUtil.toByta(1));//地图张数
			out.write(BinaryUtil.toByta(221));//地图id
			out.write(BinaryUtil.toByta(lGameTotal.size()));//总条数目
			//配对id 战队id 战队等级 游戏类型
			for (LeagueGame leagueGame : lGameTotal) {
				out.write(BinaryUtil.toByta(leagueGame.getMateId()));//配对id
				out.write(BinaryUtil.toByta(leagueGame.getTeamId()));//战队id
				out.write(BinaryUtil.toByta(leagueGame.getLevel()));//战队等级
				out.write(BinaryUtil.toByta(leagueGame.getGameType()));//游戏类型
				out.write(BinaryUtil.toByta(leagueGame.getPlayerIds().size()));//玩家数目
				for (int i = 0; i < leagueGame.getPlayerIds().size() && i < 6; i++) {
					out.write(BinaryUtil.toByta(leagueGame.getPlayerIds().get(i)));//玩家id
				}
			}
			return out.toByteArray();
		} catch (Exception e) {
			log.warn("Error in LeagueGameInfo: ", e);
			throw e;
		}
	}

	/**
	 * 选出一只轮空战队,选出配对战队,都存入对应数据
	 * @param teamsByApply
	 * @return
	 * @throws Exception
	 */
	private void teamByeOrGroup(List<Team> teamsByApply) throws Exception {
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		Random random = new Random();
		while (teamsByApply.size() > 1) {
			Team team_a = teamsByApply.remove(random.nextInt(teamsByApply.size()));
			Team team_b = teamsByApply.remove(random.nextInt(teamsByApply.size()));
			nosql.hashSet(Constants.LEAGUE_TEAM_MATE_KEY_PREFIX, team_a.getId() + "", team_b.getId() + "");
			nosql.hashSet(Constants.LEAGUE_TEAM_MATE_KEY_PREFIX, team_b.getId() + "", team_a.getId() + "");
		}
		if (teamsByApply.size() == 1) {//有一队轮空
			int teamId = teamsByApply.get(0).getId();
			mcc.delete(CacheUtil.oTeam(teamId));
			Team teamBye = getService.getTeam(teamId);

			int scoce_a = LeagueTeamTopImpl.getScoce(teamId) + 112;
			//更新战队排行榜积分
			LeagueTeamTopImpl.updataLeagueTeamTop(teamId, scoce_a);
			//获得联赛参加战队胜负积分
			LeagueTeam teama = LeagueTeamImpl.getTeamById(teamId, 1);
			//增加一场胜利
			teama.setWin(teama.getWin() + 1);
			teama.setScoce(scoce_a);//积分
			LeagueTeamImpl.updataTeam(teamId, teama);

			teamBye.setLeagueWin(teamBye.getLeagueWin() + 1);
			teamBye.setLeagueScore(teamBye.getLeagueScore() + 112);
			updateService.updateTeamInfo(teamBye);
			// 这个战队整个战场全部直接获胜
			//更新联赛战队战斗记录
			LeagueGameRecordImpl.updataGameRecords(teamId, 0, 0, 44, 0);
			LeagueGameRecordImpl.updataGameRecords(teamId, 0, 1, 26, 0);
			LeagueGameRecordImpl.updataGameRecords(teamId, 0, 2, 15, 0);
			LeagueGameRecordImpl.updataGameRecords(teamId, 0, 3, 15, 0);
			LeagueGameRecordImpl.updataGameRecords(teamId, 0, 4, 15, 0);
			//保存进轮空表
			nosql.hashSet(Constants.LEAGUE_TEAM_MATE_BYE_KEY_PREFIX, teamId + "", 44444 + "");
			//保存到数据库

		}
	}

	/**
	 * 根据轮空表处理 剩余战队的战场轮空,同时发送所有配对数据
	 * @return
	 * @return
	 * @throws Exception
	 */
	private List<LeagueGame> teamGameGroup() throws Exception {
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		//配对表
		Map<String, String> mathMap = nosql.hgetAll(Constants.LEAGUE_TEAM_MATE_KEY_PREFIX);
		//按战队配对的类
		Map<Integer, LeagueGameTeamIdMate> lgtmMap = new HashMap<Integer, LeagueGameTeamIdMate>();
		//按类型分组type_group
		if (mathMap == null)
			return new ArrayList<LeagueGame>();
		for (String key : mathMap.keySet()) {
			LeagueGameTeamIdMate lgtm = new LeagueGameTeamIdMate();//按战队配对的类
			Integer teamId = Integer.valueOf(key);//战场id
			lgtm.setTeamId(teamId);
			//返回该战队下所有参赛玩家的数据,按游戏类型
			Map<Integer, ArrayList<Integer>> membersMap = LeagueMemberImpl.getMembersMap(teamId);
			Team team = ServiceLocator.getService.getTeamById(teamId);
			if (team.getApply() == 0)
				continue;
			String teamName = "未知";
			int level = 0;
			if (team != null) {
				teamName = team.getName();
				level = team.getLevel();
			}
			for (int i = 0; i < 5; i++) {
				ArrayList<Integer> arrayList = membersMap.get(i);
				if (arrayList == null)
					continue;
				LeagueGame leagueGame = new LeagueGame();
				leagueGame.setTeamId(teamId);
				leagueGame.setGameType(LeagueUtil.leagueGameTypeTogameType(i));
				leagueGame.setLevel(level);
				leagueGame.setTeamName(teamName);
				leagueGame.setPlayerIds(arrayList);
				lgtm.getLg()[i] = leagueGame;
			}
			lgtmMap.put(teamId, lgtm);
		}
		//配对关系
		HashMap<LeagueGameTeamIdMate, LeagueGameTeamIdMate> maps = new HashMap<LeagueGameTeamIdMate, LeagueGameTeamIdMate>();
		ArrayList<String> list = new ArrayList();
		for (Entry<String, String> entry : mathMap.entrySet()) {
			Integer teamId_a = Integer.valueOf(entry.getKey());//战场aid
			Integer teamId_b = Integer.valueOf(entry.getValue());//战场bid
			if (!list.contains(teamId_a + "a")) {
				maps.put(lgtmMap.get(teamId_a), lgtmMap.get(teamId_b));
			}
			list.add(teamId_b + "a");
		}
		/*  for (Entry<String, String> entry : mathMap.entrySet()) {
		      Integer teamId_a = Integer.valueOf(entry.getKey());//战场aid
		      Integer teamId_b = Integer.valueOf(entry.getValue());//战场bid
		      maps.put(lgtmMap.get(teamId_a), lgtmMap.get(teamId_b));
		  }*/
		return teamIdMateClose(maps);
	}

	/**
	 * 战队id配对结算
	 * @param arrayList
	 * @param maps2
	 * @return
	 * @throws Exception
	 */
	private ArrayList<LeagueGame> teamIdMateClose(HashMap<LeagueGameTeamIdMate, LeagueGameTeamIdMate> maps2) throws Exception {
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		//战场配对数据缓存
		ArrayList<LeagueGame> randoMateList = new ArrayList<LeagueGame>();
		//更新战斗记录
		List<LeagueTeam> leagueTeams = new ArrayList<LeagueTeam>();
		for (Entry<LeagueGameTeamIdMate, LeagueGameTeamIdMate> entry : maps2.entrySet()) {
			LeagueGameTeamIdMate lgtm_a = entry.getKey();
			LeagueGameTeamIdMate lgtm_b = entry.getValue();
			//根据战场参赛玩家数进行轮空操作
			//双方都有参赛玩家正常结束
			//记录胜|负|积分
			LeagueTeam lt_now_a = new LeagueTeam();
			LeagueTeam lt_now_b = new LeagueTeam();
			int teamaId = lgtm_a.getTeamId();
			int teambId = lgtm_b.getTeamId();
			lt_now_a.setTeamId(teamaId);
			lt_now_b.setTeamId(teambId);
			Integer byeCode_a = 11111;
			Integer byeCode_b = 11111;
			for (int i = 0; i < 5; i++) {
				LeagueGame lGame_a = lgtm_a.getLg()[i];
				LeagueGame lGame_b = lgtm_b.getLg()[i];
				if (lGame_a != null && lGame_b != null) {//正常打
					//配对id
					int mateId = new Random().nextInt();
					lGame_a.setMateId(mateId);
					lGame_b.setMateId(mateId);
					randoMateList.add(lGame_a);
					randoMateList.add(lGame_b);
					continue;
				}
				//参赛玩家数都为空平手给0分，只有一方参赛玩家数为空则为空方判负
				//更新轮空状态码
				byeCode_a += (int) Math.pow(10, i) * (lGame_a == null ? 1 : 2);
				byeCode_b += (int) Math.pow(10, i) * (lGame_b == null ? 1 : 2);
				//更新积分
				int scoce_a = lGame_a == null ? 0 : LeagueUtil.byeScore(i);
				int scoce_b = lGame_b == null ? 0 : LeagueUtil.byeScore(i);
				//添加到战斗记录
				LeagueGameRecordImpl.updataGameRecords(lgtm_a.getTeamId(), lgtm_b.getTeamId(), i, scoce_a, scoce_b);
				LeagueGameRecordImpl.updataGameRecords(lgtm_b.getTeamId(), lgtm_a.getTeamId(), i, scoce_b, scoce_a);
				//记录胜|负|积分
				lt_now_a.setScoce(lt_now_a.getScoce() + scoce_a);
				Team a = getService.getTeam(teamaId);
				Team b = getService.getTeam(teambId);
				a.setLeagueScore(a.getLeagueScore() + scoce_a);
				b.setLeagueScore(b.getLeagueScore() + scoce_b);
				updateService.updateTeamInfo(a);
				updateService.updateTeamInfo(b);
				//记录胜|负|积分
				lt_now_b.setScoce(lt_now_b.getScoce() + scoce_b);
			}
			//轮空表
			nosql.hashSet(Constants.LEAGUE_TEAM_MATE_BYE_KEY_PREFIX, teamaId + "", byeCode_a + "");
			nosql.hashSet(Constants.LEAGUE_TEAM_MATE_BYE_KEY_PREFIX, teambId + "", byeCode_b + "");
			leagueTeams.add(lt_now_a);
			leagueTeams.add(lt_now_b);
		}
		//更新 排行榜 跟 联赛参加战队胜负
		for (LeagueTeam leagueTeam : leagueTeams) {
			int teamId = leagueTeam.getTeamId();
			int scoce = leagueTeam.getScoce();
			//更新战队排行榜积分
			if (leagueTeam.getScoce() != 0) {
				int scoce_a = LeagueTeamTopImpl.getScoce(teamId) + scoce;
				LeagueTeamTopImpl.updataLeagueTeamTop(teamId, scoce_a);
				LeagueTeamTopImpl.updataDailyScoreTop(teamId, LeagueTeamTopImpl.getDailyScoce(teamId) + scoce);
			}
			//获得联赛参加战队胜负积分
			LeagueTeam team = LeagueTeamImpl.getTeamById(teamId, 1);
			team.setWin(team.getWin() + leagueTeam.getWin());//胜
			team.setFall(team.getFall() + leagueTeam.getFall());//负
			team.setScoce(team.getScoce() + scoce);//积分
			LeagueTeamImpl.updataTeam(teamId, team);

		}
		return randoMateList;
	}

	/**
	 * 按战队配对的类
	 * @author zhaolianming
	 */
	class LeagueGameTeamIdMate {
		private int teamId;
		private LeagueGame[] lg = new LeagueGame[5];

		public int getTeamId() {
			return teamId;
		}

		public void setTeamId(int teamId) {
			this.teamId = teamId;
		}

		public LeagueGame[] getLg() {
			return lg;
		}

		public void setLg(LeagueGame[] lg) {
			this.lg = lg;
		}

	}
}

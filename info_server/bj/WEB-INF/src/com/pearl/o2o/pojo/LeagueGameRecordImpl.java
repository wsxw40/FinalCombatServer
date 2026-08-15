package com.pearl.o2o.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.service.UpdateService;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DateFormatUtil;
import com.pearl.o2o.utils.GsonUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class LeagueGameRecordImpl {
	private static NoSql noSql = ServiceLocator.nosqlService.getNosql();
	private static GetService getService = ServiceLocator.getService;
	private static UpdateService updateService = ServiceLocator.updateService;

	/**
	 * 获得当前战队比赛记录的积分
	 * @param teamId
	 * @param teambId
	 * @param gameType
	 * @return
	 * @throws Exception
	 */
	public static int[] getMyTeamScoce(int teamId, int teambId, int gameType) throws Exception {
		String key = Constants.LEAGUE_GAME_RECORD_KEY_PREFIX + teamId;
		String format = DateFormatUtil.getYMDSf().format(new Date());
		String key_2 = format + "|" + teambId + "|" + gameType;
		String value = noSql.hashGet(key, key_2);
		int[] ints = new int[2];
		if (value == null) {
			ints[0] = 0;
			ints[1] = 0;
			return ints;
		}
		String[] valueArray = value.split("\\|");
		ints[0] = Integer.valueOf(valueArray[0]);//myTeamScoce
		ints[1] = Integer.valueOf(valueArray[1]);//teambScoce
		return ints;
	}

	//"lagrs:temaid", "日期|temabid|战场type" "100|10"
	public static Map<String, LeagueGameRecord> getGameRecords(int teamId) throws Exception {
		String key = Constants.LEAGUE_GAME_RECORD_KEY_PREFIX + teamId;
		//总记录
		Map<String, String> lgrs = noSql.hgetAll(key);
		//放解析后的总记录
		Map<String, LeagueGameRecord> hashMap = new HashMap<String, LeagueGameRecord>();

		for (Entry<String, String> lgr : lgrs.entrySet()) {
			String key_now = lgr.getKey();
			String value_now = lgr.getValue();

			String[] keyArray = key_now.split("\\|");
			String time = keyArray[0];
			int teambid = Integer.valueOf(keyArray[1]);
			int gameType = Integer.valueOf(keyArray[2]);

			LeagueGameRecord lgr_now = hashMap.get(time + "|" + teambid);

			String[] valueArray = value_now.split("\\|");
			int myTeamScoce = Integer.valueOf(valueArray[0]);
			int teambScoce = Integer.valueOf(valueArray[1]);

			if (lgr_now == null) {
				LeagueGameRecord lGameRecord = new LeagueGameRecord();
				lGameRecord.setTime(time);//比赛时间日期
				lGameRecord.setTeambId(teambid);////对方战队id
				Team team = ServiceLocator.getService.getTeam(teambid);
				lGameRecord.setTeambName(team == null ? "未知战队" : team.getName());////对方战队名字
				lGameRecord.getLgrSingleS().add(new LeagueGameRecordSingle(gameType, myTeamScoce, teambScoce));
				hashMap.put(time + "|" + teambid, lGameRecord);
			} else {
				lgr_now.getLgrSingleS().add(new LeagueGameRecordSingle(gameType, myTeamScoce, teambScoce));
			}
		}
		return hashMap;
	}

	/**
	 * 更新战斗记录
	 * @param myTeamId 本战队的id
	 * @param teambId 对方战队的id
	 * @param gameType 战场类型
	 * @param myScore 本战队的积分
	 * @param scoreb 对方战队的积分
	 * @throws Exception
	 */
	public static void updataGameRecords(int myTeamId, int teambId, int gameType, int myScore, int scoreb) throws Exception {
		String format = DateFormatUtil.getYMDSf().format(new Date());
		String key_1 = Constants.LEAGUE_GAME_RECORD_KEY_PREFIX + myTeamId;
		String key_2 = format + "|" + teambId + "|" + gameType;
		noSql.hashSet(key_1, key_2, myScore + "|" + scoreb);
		FightRecord fr = new FightRecord();
		fr.setMateId(teambId);
		fr.setGameType(gameType);
		fr.setMyScore(myScore + "");
		fr.setMateScore(scoreb + "");
		fr.setTime(format);
		updataTeamGameRecord(fr, myTeamId);
	}

	public static void updataTeamGameRecord(FightRecord fr, int teamId) throws Exception {
		Team team = getService.getTeam(teamId);
		if (null != team) {
			List<FightRecord> frs = GsonUtil.getFightRecoreds(team.getFightRecord());
			for (FightRecord f : frs) {
				if (f.getGameType() == fr.getGameType() && f.getTime().equals(fr.getTime())) {
					frs.remove(f);
					break;
				}
			}
			frs.add(fr);
			team.setFightRecord(GsonUtil.FightRecoredsToString(frs));
			updateService.updateTeamInfo(team);
		}
	}

}
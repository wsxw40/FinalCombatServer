package com.pearl.o2o.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 联赛成员
 * @author zhaolianming
 */
//(lami:teamid) (pid) (战场type|积分|"名字")
public class LeagueMemberImpl {
	private static NoSql noSql = ServiceLocator.nosqlService.getNosql();
	private static GetService getService = ServiceLocator.getService;

	/**
     * 
     */
	public static int getSize(int teamId) throws Exception {
		return (int) noSql.hashlen(Constants.LEAGUE_ATTEND_MEMBER_INFO_KEY_PREFIX + teamId);
	}

	/**
	 * 返回该战队下所有参赛玩家的数据
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<LeagueMember> getMembers(int teamId) throws Exception {
		ArrayList<LeagueMember> MemberList = new ArrayList<LeagueMember>();
		Map<String, String> members = noSql.hgetAll(Constants.LEAGUE_ATTEND_MEMBER_INFO_KEY_PREFIX + teamId);
		for (Entry<String, String> member : members.entrySet()) {
			Integer playerId = Integer.valueOf(member.getKey());
			Player p = getService.getPlayerById(playerId);
			String values = member.getValue();
			String[] valueArray = values.split("\\|");
			LeagueMember leagueMember = new LeagueMember();
			if (valueArray.length == 3) {
				leagueMember.setPlayerId(playerId);
				leagueMember.setGameType(Integer.valueOf(valueArray[0]));
				leagueMember.setScore(Integer.valueOf(valueArray[1]));
				leagueMember.setName(p.getName());
				MemberList.add(leagueMember);
			}
		}
		return MemberList;
	}

	/**
	 * 按游戏类型返回该战队下所有参赛玩家的数据
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	public static Map<Integer, ArrayList<Integer>> getMembersMap(int teamId) throws Exception {
		ArrayList<LeagueMember> members = getMembers(teamId);
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for (LeagueMember leagueMember : members) {
			int gameType = leagueMember.getGameType();
			if (map.get(gameType) == null) {
				ArrayList<Integer> playerIds = new ArrayList<Integer>();
				map.put(gameType, playerIds);
			}
			map.get(gameType).add(leagueMember.getPlayerId());
		}
		return map;
	}

	/**
	 * 按游戏类型返回该战队下所有参赛玩家的数据,同时检验名单
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	public static Map<Integer, ArrayList<LeagueMember>> getCheckMembersMap(int teamId) throws Exception {
		ArrayList<LeagueMember> members = getMembers(teamId);
		Map<Integer, ArrayList<LeagueMember>> map = new HashMap<Integer, ArrayList<LeagueMember>>();
		for (LeagueMember leagueMember : members) {
			int gameType = leagueMember.getGameType();
			int playerId = leagueMember.getPlayerId();
			if (map.get(gameType) == null) {
				ArrayList<LeagueMember> playerIds = new ArrayList<LeagueMember>();
				map.put(gameType, playerIds);
			}
			if (getService.getPlayerTeam(teamId, playerId) == null) {
				//表示这人不在战队,所以从redis里中的名单map中删除
				delPlayerById(teamId, playerId);
			} else {
				map.get(gameType).add(leagueMember);
			}
		}
		return map;
	}

	/**
	 * 存入参赛玩家的数据
	 * @param teamId
	 * @param playerId
	 * @throws Exception
	 */
	public static void updataMember(int teamId, LeagueMember leagueMember) throws Exception {
		int playerId = leagueMember.getPlayerId();
		String value = leagueMember.getGameType() + "|" + leagueMember.getScore() + "|" + leagueMember.getName();
		noSql.hashSet(Constants.LEAGUE_ATTEND_MEMBER_INFO_KEY_PREFIX + teamId, playerId + "", value);
	}

	/**
	 * 删除teamId 联赛参加人员名单 hash集合
	 * @param teamId
	 * @param playerId
	 * @throws Exception
	 */
	public static void delTeamById(int teamId) throws Exception {
		noSql.delete(Constants.LEAGUE_ATTEND_MEMBER_INFO_KEY_PREFIX + teamId);
	}

	/**
	 * 删除teamId站队playerId的玩家
	 * @param teamId
	 * @param playerId
	 * @throws Exception
	 */
	public static void delPlayerById(int teamId, int playerId) throws Exception {
		noSql.hashdel(Constants.LEAGUE_ATTEND_MEMBER_INFO_KEY_PREFIX + teamId, playerId + "");
	}

	/**
	 * 根据teamId和playerId 获得玩家数据
	 * @param teamId
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public static LeagueMember getPlayerById(int teamId, int playerId) throws Exception {
		String values = noSql.hashGet(Constants.LEAGUE_ATTEND_MEMBER_INFO_KEY_PREFIX + teamId, playerId + "");
		LeagueMember leagueMember = new LeagueMember();
		if (values == null) {
			leagueMember.setPlayerId(playerId);
			leagueMember.setGameType(5);
			leagueMember.setScore(0);
			leagueMember.setName(getService.getPlayerById(playerId).getName());
			updataMember(teamId, leagueMember);
			return leagueMember;
		}
		String[] valueArray = values.split("\\|");
		if (valueArray.length == 3) {
			leagueMember.setPlayerId(playerId);
			leagueMember.setGameType(Integer.valueOf(valueArray[0]));
			leagueMember.setScore(Integer.valueOf(valueArray[1]));
			leagueMember.setName(valueArray[2]);
		}
		return leagueMember;
	}

	/**
	 * 重置该战队联赛参加人员名单
	 * @param teamId
	 * @throws Exception
	 */
	public static void resetTeamById(int teamId) throws Exception {
		Team team = getService.getTeam(teamId);
		List<PlayerTeam> playerTeams = getService.getPlayerTeamByTeamId(team.getId());
		for (PlayerTeam playerTeam : playerTeams) {
			int playerId = playerTeam.getPlayerId();
			String name = getService.getPlayerById(playerId).getName();
			String value = 5 + "|" + 0 + "|" + name;
			noSql.hashSet(Constants.LEAGUE_ATTEND_MEMBER_INFO_KEY_PREFIX + teamId, playerId + "", value);
		}

	}

	/**
	 * 替换游戏类型
	 * @param playerId
	 * @param gameType
	 * @param teamId
	 * @param membersMap
	 * @return
	 * @throws Exception
	 */
	public static Map<Integer, ArrayList<LeagueMember>> flush(final int playerId, final int gameType, Integer teamId, Map<Integer, ArrayList<LeagueMember>> membersMap) throws Exception {
		LeagueMember leagueMember = LeagueMemberImpl.getPlayerById(teamId, playerId);
		//获得该类型所有的玩家数据
		ArrayList<LeagueMember> nowList = membersMap.get(leagueMember.getGameType());
		if (nowList != null) {
			for (LeagueMember lMember : nowList) {
				if (lMember.getPlayerId() == playerId) {
					//删除原来的
					nowList.remove(lMember);
					break;
				}
			}
		}
		ArrayList<LeagueMember> tagList = membersMap.get(gameType);
		if (tagList == null) {
			tagList = new ArrayList<LeagueMember>();
			membersMap.put(gameType, tagList);
		}
		//放到新的类型里
		tagList.add(leagueMember);
		leagueMember.setGameType(gameType);
		LeagueMemberImpl.updataMember(teamId, leagueMember);
		return membersMap;
	}
}

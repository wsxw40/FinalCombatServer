package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TimeTrack;

public class GetTeamTop extends BaseClientServlet {

	private static final long serialVersionUID = -7902882472176853249L;
	static Logger log = LoggerFactory.getLogger(GetTeamTop.class.getName());
	static Logger timeTrackLog = LoggerFactory.getLogger("timeTrack");
	private static final String[] paramNames = { "pid", "pvc", "cty", "type","page", "self", "name" };
	private int pageSize = Constants.TEAM_TOP_PAGESIZE;
	private int maxSize = Constants.TEAM_TOP_NUM;

	protected String innerService(String... args) {
		try {
		
			int playerId = StringUtil.toInt(args[0]);
			String province = args[1];
			String city = args[2];
			int type = StringUtil.toInt(args[3]);
			int page = StringUtil.toInt(args[4]);
			int self = StringUtil.toInt(args[5]);
			String name = args[6];
			String result = "";
			List<String> rankEntry = new ArrayList<String>();
			int selectIndex = 0;
			int pages = 0;
			int total = 0;
			Player player = getService.getSimplePlayerById(playerId);
			
			int start = 0;
			List<Team> retTeams = new ArrayList<Team>();
			if (self == 1) {//定位功能，暂为开放
//				Team myTeam = new Team();
//				myTeam.setId(player.getTeamId());
//				retTeams = getService.getSortedTeamsByProvinceCityType(province, city, name, type, 0, maxSize);
//				total = retTeams.size();
//				int teamRank = retTeams.indexOf(myTeam) + 1;
//				if (teamRank != 0) {
//					if (teamRank == 0) {
//						start = 0;
//						page = 1;
//					} else if (teamRank % pageSize == 0) {
//						start = (int) (teamRank / pageSize - 1) * pageSize;
//						page = (int) (teamRank / pageSize);
//					} else {
//						start = (int) (teamRank / pageSize) * pageSize;
//						page = (int) (teamRank / pageSize + 1);
//					}
//				}
//				if (teamRank % pageSize == 0) {
//					selectIndex = pageSize;
//				} else {
//					selectIndex = (int) teamRank % pageSize;
//				}
//				int endIndex = start + pageSize;
//				retTeams = retTeams.subList(start,endIndex > retTeams.size() ? retTeams.size(): endIndex);
			} else {
				TimeTrack tt = new TimeTrack(timeTrackLog);
				if (StringUtil.isEmptyString(province)&& StringUtil.isEmptyString(city) && StringUtil.isEmptyString(name)) {//如果是全部，则会从redis中获取
					NoSql nosql = nosqlService.getNosql();
					String key = Constants.TEAMTOP_KEY_PREFIX + type;
					String[] teamIds = {};
					if(type==Constants.TEAM_TOP_TYPE.NEW.getValue()){
						total = (int) nosql.llen(key);
						if (total > maxSize) {
							total = maxSize;
						}
						pages = total % pageSize == 0?total / pageSize:(total / pageSize) + 1;
						if (pages < 1) {
							pages = 1;
						}
						page = page < 1 ? 1 : page > pages ? pages : page;
						start = (page - 1) * pageSize;
						teamIds = nosql.lrange(key, start, start + pageSize-1).toArray(teamIds);
					}else{
						total = (int) nosql.zCard(key);
						if (total > maxSize) {
							total = maxSize;
						}
						pages = total % pageSize == 0?total / pageSize:(total / pageSize) + 1;
						if (pages < 1) {
							pages = 1;
						}
						page = page < 1 ? 1 : page > pages ? pages : page;
						start = (page - 1) * pageSize;
						teamIds = nosql.revRangeSortedSet(key, start, start + pageSize-1).toArray(teamIds);
					}
					tt.debug("Nosql");
					for (String teamId : teamIds) {
						Team team = getService.getTeamById(StringUtil.toInt(teamId));
						if(team!=null){
							retTeams.add(team);
						}else{//如果该战队已经不存在，则删除相应排名记录
							if(type==Constants.TEAM_TOP_TYPE.NEW.getValue()){
								nosql.removeFromList(key, 1, teamId);
							}else{
								nosql.zRem(key, teamId);
							}
							log.warn("GetTeamTop/TeamNull:\t"+teamId);
						}
					}
					tt.debug("Mcc");
				}else{
					total = getService.getTeamTotalNumByNameProvinceCity(name,province, city);
					pages = total % pageSize == 0?total / pageSize:(total / pageSize) + 1;
					if (pages < 1) {
						pages = 1;
					}
					page = page < 1 ? 1 : page > pages ? pages : page;
					start = (page - 1) * pageSize;
					retTeams = getService.getSortedTeamsByProvinceCityType(province, city, name, type, start, pageSize);
				}
			}
			int rank = start;
			TimeTrack tt = new TimeTrack(timeTrackLog);
			for (Team team : retTeams) {
				if(team.getMemberList()==null||team.getMemberList().isEmpty()){
					getService.setTeamMember(team);
					team.setFight(getService.getTeamFight(team));
				}
				rankEntry.add(generateTeamTopStr(team, ++rank));
			}
			tt.debug("Fight");
			result = Converter.teamTop(page, pages, rankEntry,
					player.getTeamId(), selectIndex);
			return result;
		} catch (Exception e) {
			log.warn("GetTeamTop/Error", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	private String generateTeamTopStr(Team team, int rank) {
		StringBuilder result = new StringBuilder();
		result.append("\"").append(rank).append("\"").append(",").append("\"")
				.append(team.getName()).append("\"").append(",").append("\"")
				.append(team.getLogo()).append("\"").append(",").append("\"")
				.append(team.getGameRatio()).append("\"").append(",")
				.append("\"").append(team.getGameWin()).append("\"")
				.append(",").append("\"").append(team.getGameTotal())
				.append("\"").append(",").append("\"").append(team.getLevel())
				.append("\"").append(",").append("\"").append(team.getFight())
				.append("\"").append(",").append("\"").append(team.getNumber())
				.append("\"").append(",").append("\"").append(team.getSize())
				.append("\"").append(",").append("\"").append(team.getRank())
				.append("\"").append(",").append("\"").append(team.getCity())
				.append("\"").append(",").append("\"")
				.append(CommonUtil.dateFormatDate.format(team.getCreateTime()))
				.append("\"").append(",").append("\"").append(team.getId())
				.append("\"").append(",").append("\"")
				.append(team.getLeaderName()).append("\"");
		return result.toString();
	}
}

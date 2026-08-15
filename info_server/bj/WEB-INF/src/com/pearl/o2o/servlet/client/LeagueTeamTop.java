package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.LeagueTeam;
import com.pearl.o2o.pojo.LeagueTeamImpl;
import com.pearl.o2o.pojo.LeagueTeamTopImpl;
import com.pearl.o2o.pojo.Page;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 联赛战队排行榜
 * @author zhaolianming
 */
public class LeagueTeamTop extends BaseClientServlet {
	//rpc.safecall("league_team_top",{pid = 10638000,page = 1,t = 1},function(data) end)
	private static final long serialVersionUID = -1633529949445850561L;
	private static Logger log = LoggerFactory.getLogger(LeagueTeamTop.class.getName());
	private static final String[] paramNames = { "pid", "page", "t" };

	protected String innerService(String... args) {
		try {
			final int playerId = StringUtil.toInt(args[0]);// 获得玩家id
			int pageNow = StringUtil.toInt(args[1]);//第几页
			final int type = StringUtil.toInt(args[2]);//1当前赛季 0上个赛季
			Player player = getService.getPlayerById(playerId);
			Integer myTeamId = player.getTeamId();
			LeagueTeam myLeagueTeam = null;
			Page page = new Page(8, LeagueTeamTopImpl.getNumber(type), pageNow);
			int rankIndex = page.getStart();
			List<String> entryList = new ArrayList<String>();
			if (type == 1) {//当前赛季
				myLeagueTeam = LeagueTeamImpl.getTeamById(myTeamId, type);
				List<LeagueTeam> leagueTeams = LeagueTeamTopImpl.leagueTeamTopPage(page.getStart(), page.getEnd());
				for (LeagueTeam leagueTeam : leagueTeams) {
					rankIndex++;
					String str = LeagueTeamTopImpl.joint(leagueTeam, rankIndex);
					if (StringUtils.isNotBlank(str))
						entryList.add(str);
				}
			} else {//上个赛季
				myLeagueTeam = LeagueTeamImpl.getTeamById(myTeamId, type);
				List<LeagueTeam> leagueTeams = LeagueTeamTopImpl.oldLeagueTeamTopPage(page.getStart(), page.getEnd());
				for (LeagueTeam leagueTeam : leagueTeams) {
					rankIndex++;
					String str = LeagueTeamTopImpl.joint(leagueTeam, rankIndex);
					if (StringUtils.isNotBlank(str))
						entryList.add(str);
				}
			}
			return Converter.LeagueTeamTop(page.getPageNow(), page.getPageNum(), entryList, myTeamId, myLeagueTeam.getIndex(), myLeagueTeam.getScoce());
		} catch (Exception e) {
			log.warn("Error in " + LeagueAttendMemberInfo.class.getName() + ": ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}

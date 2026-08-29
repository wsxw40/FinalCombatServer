package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.LeagueMember;
import com.pearl.o2o.pojo.LeagueMemberImpl;
import com.pearl.o2o.pojo.Page;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 战队成员积分排行榜
 * @author zhaolianming
 */
public class LeagueMemberTop extends BaseClientServlet {
	//rpc.safecall("league_member_top",{pid = 10638000,page = 1,t = 1},function(data) end)
	private static final long serialVersionUID = -1633529949445850561L;
	private static Logger log = LoggerFactory.getLogger(LeagueMemberTop.class.getName());
	private static final String[] paramNames = { "pid", "page", "t" };

	protected String innerService(String... args) {
		try {
			final int playerId = StringUtil.toInt(args[0]);// 获得玩家id
			int pageNow = StringUtil.toInt(args[1]);//第几页
			final int type = StringUtil.toInt(args[2]);//类型现在无用
			Player player = getService.getPlayerById(playerId);
			Integer teamId = player.getTeamId();

			ArrayList<LeagueMember> members = LeagueMemberImpl.getMembers(teamId);
			if (members.size() == 0) {
				return Converter.LeagueMemberTop(1, 1, new ArrayList<String>());
			}
			Page page = new Page(17, members.size(), pageNow);
			List<LeagueMember> subList = members.subList(page.getStart(), page.getEnd());
			List<String> entryList = new ArrayList<String>();
			for (LeagueMember leagueMember : subList) {
				entryList.add("\"" + leagueMember.getGameType() + "\",\"" + leagueMember.getScore() + "\",\"" + leagueMember.getName() + "\"");
			}
			return Converter.LeagueMemberTop(page.getPageNow(), page.getPageNum(), entryList);
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

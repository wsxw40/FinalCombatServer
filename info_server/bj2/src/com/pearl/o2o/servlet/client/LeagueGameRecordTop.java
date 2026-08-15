package com.pearl.o2o.servlet.client;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.LeagueGameRecord;
import com.pearl.o2o.pojo.LeagueGameRecordImpl;
import com.pearl.o2o.pojo.LeagueTeam;
import com.pearl.o2o.pojo.LeagueTeamTopImpl;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
/**
 * 联赛战队战斗记录
 * @author zhaolianming
 *
 */
public class LeagueGameRecordTop extends BaseClientServlet {
	//rpc.safecall("league_game_record_top",{pid = 10638000},function(data) end)
	private static final long serialVersionUID = -1633529949445850561L;
	private static Logger log = LoggerFactory.getLogger(LeagueGameRecordTop.class.getName());
	private static final String[] paramNames = {"pid"};
	protected String innerService(String... args) {
		try{
			final int playerId = StringUtil.toInt(args[0]);// 获得玩家id
            Player player = getService.getPlayerById(playerId);
            Integer teamId = player.getTeamId();
            LeagueTeam leagueTeam = LeagueTeamTopImpl.getLeagueTeam(teamId);
            int myTeamIndex = leagueTeam.getIndex();//当前站队排行
            Map<String, LeagueGameRecord> gameRecordMap = LeagueGameRecordImpl.getGameRecords(teamId);
            List<LeagueGameRecord> gameRecordList = new ArrayList<LeagueGameRecord>();
            for (LeagueGameRecord gameRecord : gameRecordMap.values()) {
                gameRecord.joint();//拼接 
                gameRecordList.add(gameRecord);
            }
            return Converter.LeagueGameRecordTop(gameRecordList, myTeamIndex);
		}catch(Exception e){
			log.warn("Error in "+LeagueAttendMemberInfo.class.getName()+": " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}

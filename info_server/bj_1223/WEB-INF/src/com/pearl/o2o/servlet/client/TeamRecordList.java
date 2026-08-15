
package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamRecord;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * @author bobby
 */
public class TeamRecordList extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(TeamRecordList.class.getName());
	private static final String[] paramNames = {"tid"};
	/**
	 * 获取战队详细信息
	 */
	protected String innerService(String... args) {
		try{
			int teamId = StringUtil.toInt(args[0]);
			List<TeamRecord> list=new ArrayList<TeamRecord>();
			Map<Integer, TeamRecord>  map=teamService.getTeamRecordByTeamId(teamId);
			for(Iterator<Entry<Integer, TeamRecord>> it=map.entrySet().iterator();it.hasNext();){
				Entry<Integer, TeamRecord>  e=it.next();
				TeamRecord t=e.getValue();
				Team team=getService.getTeam(t.getBTeamId());
				if(team!=null){
					int headerid = ServiceLocator.getService.getTeamDao().getTeamHeader(t.getBTeamId());
					Player teamLeader = getService.getPlayerById(headerid);
					t.setBTeamName(team.getName());
					LevelInfo li=teamService.getLevelInfoById(t.getLevelId());
					t.setLevelType(li.getType());
					t.setLevelName(li.getName());
					t.setBTeamLeader(teamLeader.getName());
					list.add(t);
					Collections.sort(list,Collections.reverseOrder());
				}else{
					logger.warn(ExceptionMessage.TEAM_RECORD_WARN+" TeamRecord="+t.getId()+" teamId="+teamId+" bTeamId="+t.getBTeamId());
				}
			}
			return Converter.teamRecord(list);
		}catch (Exception e) {
			logger.warn(e.getMessage(),e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}

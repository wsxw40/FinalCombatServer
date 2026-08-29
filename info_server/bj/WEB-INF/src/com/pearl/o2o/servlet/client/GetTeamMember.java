
package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ComparisonChain;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author bobby
 */
public class GetTeamMember extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	private static Logger logger=LoggerFactory.getLogger(GetTeamMember.class.getName());
	private static final String[] paramNames = {"tid","page"};
	
	private static Comparator<PlayerTeam> playerTeamComparator = new Comparator<PlayerTeam>() {
		@Override
		public int compare(PlayerTeam o1, PlayerTeam o2) {
			return ComparisonChain.start()
					.compare(o2.getOnline(), o1.getOnline())
					.compare(o2.getContribution(), o1.getContribution())
					.compare(o2.getJob(), o1.getJob())
					.compare(o2.getTimes(), o1.getTimes())
					.compare(o2.getScore(), o1.getScore())
//					.compare(o2.getFight(), o1.getFight())
//					.compare(o2.getKillNum(), o1.getKillNum())
//					.compare(o2.getDeadNum(), o1.getDeadNum())
//					.compare(o2.getAssist(), o1.getAssist())
					.compare(o1.getCreateTime(), o2.getCreateTime())
					.result();
		}
	};
	
	private static final int PageSize = 9;
	
	protected String innerService(String... args) {
		try{
			int teamId = StringUtil.toInt(args[0]);
			int page = StringUtil.toInt(args[1]);
			
			Team team=getService.getTeam(teamId);
			if(team!=null){
				List<PlayerTeam> list=getService.getPlayerTeamByTeamIdSimple(team.getId());
//				List<PlayerTeam> onlineList=new ArrayList<PlayerTeam>();
//				List<PlayerTeam> offlineList=new ArrayList<PlayerTeam>();
				if(list!=null){
					for(PlayerTeam pt:list){
						PlayerLocation pl=mcc.get(CacheUtil.oPlayerLocation(pt.getPlayerId()),Constants.CACHE_TIMEOUT);
//						getService.joinPlayerAndPlayerTeamWithoutCard(pt);
						if(pl!=null){
							pt.setOnline(1);
//							onlineList.add(pt);
						}
						else{
							pt.setOnline(0);
//							offlineList.add(pt);
						}
					}
				}else{
					list=new ArrayList<PlayerTeam>();
				}
				Collections.sort(list,playerTeamComparator);
//				onlineList.addAll(offlineList);
				//分页
				int pages = list.size()/PageSize;
				if(list.size()%PageSize!=0){
					pages ++;
				}
				//only visible member do joinPlayer
				List<PlayerTeam> memberList=new ArrayList<PlayerTeam>();
				if(page != -1){
					int endIndex = page * PageSize;
					int startIndex = (page - 1) * PageSize;
					memberList=list.subList(startIndex, endIndex > list.size()?list.size():endIndex);
				}else{
					memberList=list;
					
				}
				for(PlayerTeam pt:memberList){
					getService.joinPlayerAndPlayerTeamWithoutCard(pt);
				}
				team.setMemberList(memberList);
				return Converter.teamMember(team, page, pages);
			}else{
				logger.warn("not find team:teamId="+teamId);
				return Converter.error(ExceptionMessage.NOT_FIND_TEAM);
			}
			
			
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

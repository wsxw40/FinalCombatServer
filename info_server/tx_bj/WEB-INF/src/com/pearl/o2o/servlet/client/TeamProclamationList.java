
package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamProclamation;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author
 */
public class TeamProclamationList extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(TeamProclamationList.class.getName());
	private static final String[] paramNames = {"tid"};
	/**
	 * 获取战队公告信息
	 */
	protected String innerService(String... args) {
		try{
//			LoadingCache<Integer, Player> cache = CacheBuilder.newBuilder().<Integer, Player>build(new CacheLoader<Integer, Player>() {
//				@Override
//				public Player load(Integer arg0) throws Exception {
//					return getService.getSimplePlayerById(arg0);
//				}
//			});
			List <TeamProclamation> list=new ArrayList<TeamProclamation>();
			int teamId = StringUtil.toInt(args[0]);
			Map<Integer,TeamProclamation> map=teamService.getTeamProclamationsByTeamId(teamId);
			if(map.entrySet().size()>0){
				for(Iterator<Entry<Integer, TeamProclamation>> it=map.entrySet().iterator();it.hasNext();){
					Entry<Integer, TeamProclamation>  e=it.next();
					TeamProclamation t=e.getValue();
					Player p=getService.getSimplePlayerById(t.getPlayerId());
					if(p==null){
						 Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
					}
					t.setIcon(p.getIcon());
					t.setNikeName(p.getName());
					list.add(t);
				}
			}
			Collections.sort(list,Collections.reverseOrder());
			return Converter.teamProclamation(list);
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

package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPartnerList extends BaseClientServlet {
	private static final long serialVersionUID = -7186146783164259451L;
	static Logger log = LoggerFactory.getLogger(GetPartnerList.class.getName());
	private String[] paramNames={"uid","pid","displayOnline"};
	//请求战斗伙伴时相当于取出50个人的信息，这个需要一定的时间
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			Integer playerId = StringUtil.toInt(args[1]);
			int displayOnline=StringUtil.toInt(args[2]);
			Player self = getService.getSimplePlayerById(playerId);
			List<Integer> ids = nosqlService.getRecentPlayerIds(self.getId(), self.getName());
			List<Player> friendPartners = new ArrayList<Player>();
			for (Integer id : ids) {
				Player p=getService.getSimplePlayerById(id);
				if(p!=null){
					PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(id),Constants.CACHE_TIMEOUT);
					if (location != null) {
						p.setOnline(1);
					}
					friendPartners.add(p);
				}
			}
			Collections.sort(friendPartners, new Comparator<Player>(){
				@Override
				public int compare(Player o1, Player o2) {
					return (o1.getOnline()>=o2.getOnline()?-1:1);
				}
			});
			
				return Converter.partnerList(friendPartners);
		}
		catch(Exception e){
			log.warn("Error in GetFriendList: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}

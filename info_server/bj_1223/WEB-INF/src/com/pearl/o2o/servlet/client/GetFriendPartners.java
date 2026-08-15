package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetFriendPartners extends BaseClientServlet {
	static Logger log = LoggerFactory.getLogger(GetFriendPartners.class.getName());
	private static final long serialVersionUID = -6836692174398117141L;
	private String[] paramNames={"uid","cid"};
	@Override
	protected String innerService(String... args) {
		try{
			int uid = StringUtil.toInt(args[0]);
			int cid = StringUtil.toInt(args[1]);
			Player self = getService.getSimplePlayerById(cid);
			List<Integer> ids = nosqlService.getRecentPlayerIds(self.getId(), self.getName());
			List<Player> friendPartners = new ArrayList<Player>();
			
			for (Integer id : ids) {
				Player p=getService.getPlayerById(id);
				PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(id),Constants.CACHE_TIMEOUT);
				if (location != null) {
					p.setOnline(1);
				}
				friendPartners.add(p);
			}
			return Converter.friendPartners(friendPartners);
		}
		catch (Exception e) {
			log.warn("exception in get friends news",e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}

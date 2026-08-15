package com.pearl.o2o.servlet.client;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

import edu.emory.mathcs.backport.java.util.Collections;

public class GetFriendRankList extends BaseClientServlet {
	private static final long serialVersionUID = -7186146783164259451L;
	static Logger log = LoggerFactory.getLogger(GetFriendRankList.class.getName());
	private String[] paramNames={"uid","cid"};
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			Integer playerId = StringUtil.toInt(args[1]);
			//List<Friend> list = getService.getFriendRankList(playerId);
			List<Friend> myFriends = getService.getFriendList(playerId, Constants.FRIEND);
			
			Player self = getService.getSimplePlayerById(playerId);
			//convert self to a friend
			Friend selfAsFriend = new Friend();
			selfAsFriend.setFriendId(self.getId());
			selfAsFriend.setName(self.getName());
			selfAsFriend.setRank(self.getRank());
			
			myFriends.add(selfAsFriend);
			for (Friend friend : myFriends){
				String key = Constants.PLAYERTOP_RANK_KEY_PREFIX + "kCommon" + ":" + friend.getFriendId();
				String gameRank = nosqlService.getNosql().get(key);
				if (gameRank != null){
					friend.setGameRank(Integer.valueOf(gameRank));
				}
			}
			
			Collections.sort(myFriends,new Comparator<Friend>() {
				@Override
				public int compare(Friend f1, Friend f2) {
					return Integer.valueOf(f1.getGameRank()).compareTo(Integer.valueOf(f2.getGameRank()));
				}
			});
			
			
			return Converter.friendRankList(myFriends);
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

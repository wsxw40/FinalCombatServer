package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Receiver;

public class CharacterOffline extends BaseServerServlet {
	private static final long serialVersionUID = 6032163311968210585L;
	static Logger logger = Logger.getLogger(CharacterOffline.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		OutputStream out = res.getOutputStream();		
		InputStream in = req.getInputStream();
		byte[] buffer = new byte[32];
		try{
			Receiver r = new Receiver(in, buffer);
			int userId=r.readInt();
			int playerId=r.readInt();
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sPlayer(playerId));
			mcc.delete(CacheUtil.oPlayerList(userId));
			mcc.delete(CacheUtil.sPlayerList(userId));
			mcc.delete(CacheUtil.oPlayerLocation(playerId));
			Player player=getService.getPlayerById(userId, playerId);
			List<Friend> list=getService.getFriendListForMsg(userId, playerId);
			for(Friend friend:list){
				if(mcc.get(CacheUtil.oPlayerLocation(friend.getPlayerId()))!=null){
				soClient.message(friend.getName(), CommonUtil.messageFormat(CommonMsg.FRIEND_OFFLINE,player.getName()));
				}
			}
			
		}catch (Exception e) {
			logger.error("Error in CharacterOnline: ", e);
			return;
		}
	}
	

}

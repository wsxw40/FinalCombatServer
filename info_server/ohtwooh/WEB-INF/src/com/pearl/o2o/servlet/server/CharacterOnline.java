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
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Receiver;

public class CharacterOnline extends BaseServerServlet {
	private static final long serialVersionUID = -186025727266294629L;
	static Logger logger = Logger.getLogger(CharacterOnline.class.getName());
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
			String ip=r.readString();
			Player player=getService.getPlayerById(userId, playerId);
			if(player==null||player.getId()==null){
				out.write(BinaryUtil.toByta(0));
				out.write(BinaryUtil.toByta(""));
				out.write(BinaryUtil.toByta((byte)0));
				out.write(BinaryUtil.toByta(""));
				out.write(BinaryUtil.toByta(0));
			}else{
				PlayerLocation location=new PlayerLocation(playerId,0,0);
				mcc.set(CacheUtil.oPlayerLocation(playerId), 60, location);
				player.setLastLoginIp(ip);
				updateService.updatePlayerWhileOnline(player);
				mcc.delete(CacheUtil.oPlayerList(userId));
				mcc.delete(CacheUtil.sPlayerList(userId));
				List<Friend> list=getService.getFriendListForMsg(userId, playerId);
				for(Friend friend:list){
					if(mcc.get(CacheUtil.oPlayerLocation(friend.getPlayerId()))!=null){
					soClient.message(friend.getName(), CommonUtil.messageFormat(CommonMsg.FRIEND_ONLINE, player.getName()));
					}
				}
				out.write(BinaryUtil.toByta(player.getId()));
				out.write(BinaryUtil.toByta(player.getName()));
				out.write(BinaryUtil.toByta(Constants.GENDER_MALE.equals(player.getGender())?(byte)0:(byte)1));
				out.write(BinaryUtil.toByta(player.getTeam()));
				out.write(BinaryUtil.toByta(player.getRank()));
			}
		}catch (Exception e) {
			logger.error("Error in CharacterOnline: ", e);
			return;
		}
	}

}

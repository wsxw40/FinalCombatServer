package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.Constants;

public class GetFriendList extends BaseClientServlet {
	private static final long serialVersionUID = -7186146783164259451L;
	static Logger log = Logger.getLogger(GetFriendList.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			Integer playerId = StringUtil.toInt(req.getParameter("cid"));
			List<Friend> result = getService.getFriendList(userId, playerId, Constants.BOOLEAN_NO); 
			for(Friend fri:result){
				PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(fri.getFriendId()));
				if (location != null) {
					Channel channel = getService.getChannel(location.getChannel());
					if (channel != null) {
						Server server = getService.getServer(channel.getServerId());
						if (server != null) {
							fri.setChannel(channel.getName());
							fri.setServer(server.getName());
							fri.setRoom(location.getRoom());
						}
					}
				}
			}
			out.write(Converter.friendList(result));
		}
		catch(Exception e){
			log.error("Error in GetFriendList: " + e);
			e.printStackTrace();
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}		
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
		
	}
}

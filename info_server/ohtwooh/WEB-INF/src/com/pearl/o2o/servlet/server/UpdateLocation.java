package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Receiver;

public class UpdateLocation extends BaseServerServlet {

	private static final long serialVersionUID = -5614727816053597842L;
	static Logger logger = Logger.getLogger(UpdateLocation.class.getName());

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		InputStream in = req.getInputStream();
		byte[] buffer = new byte[32];
		try{
			Receiver r = new Receiver(in, buffer);
			int size=r.readInt();
			logger.error("size==="+size);
			for(int i=0;i<size;i++){
				int playerId=r.readInt();
				int channelId=r.readInt();
				int roomId=r.readInt();
				logger.error("location==="+playerId+" "+channelId+" "+roomId);
				PlayerLocation location=new PlayerLocation(playerId,channelId,roomId);
				mcc.set(CacheUtil.oPlayerLocation(playerId), Constants.CACHE_ITEM_TIMEOUT, location);
			}
		}catch(Exception e){
			logger.error("Error in UpdateLocation: " + e);
		}	
	}
}

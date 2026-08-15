package com.pearl.o2o.servlet.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.Constants;

public class UpdateLocation extends BaseServerServlet {

	private static final long serialVersionUID = -5614727816053597842L;
	private static Logger logger = LoggerFactory.getLogger(UpdateLocation.class.getName());

	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		try{
//			int size=r.readInt();
//			for(int i=0;i<size;i++){
//				int playerId=r.readInt();
//				int channelId=r.readInt();
//				int roomId=r.readInt();
//				PlayerLocation location=new PlayerLocation(playerId,channelId,roomId);
////				mcc.set(CacheUtil.oPlayerLocation(playerId), Constants.CACHE_ITEM_TIMEOUT, location);
//				logger.error("access updatelocation ----------------------------------");
//			}
			return Constants.EMPTY_BYTE_ARRAY;
		}catch(Exception e){
			logger.warn("Error in UpdateLocation: " + e);
			throw e;
		}	
	}
}

package com.pearl.o2o.servlet.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.Constants;

public class PlayerProfile extends BaseServerServlet {
	private static final long serialVersionUID = 6032163311968210585L;
	static Logger logger = LoggerFactory.getLogger(PlayerProfile.class.getName());
	
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		try{
			int uid =  r.readInt();
			int cid = r.readInt();
			String profile = r.readString();
			
			updateService.updatePlayerProfile(uid, cid, profile);
			
		}catch (Exception e) {
			logger.warn("Error in update Player profile: ", e);
			throw e;
		}finally{
			return Constants.EMPTY_BYTE_ARRAY;
		}
	}
}

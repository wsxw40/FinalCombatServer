package com.pearl.o2o.servlet.server;

import java.io.IOException;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.Constants;

public class UserProfile extends BaseServerServlet {
	private static final long serialVersionUID = 6032163311968210585L;
	static Logger logger = LoggerFactory.getLogger(UserProfile.class.getName());
	
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		try{
			int uid =  r.readInt();
			String profile = r.readString();
			updateService.updateUserProfile(uid, profile);
			
		}catch (Exception e) {
			logger.warn("Error in update user profile: ", e);
			throw e;
		}
		return Constants.EMPTY_BYTE_ARRAY;
	}
}

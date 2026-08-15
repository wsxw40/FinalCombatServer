package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;



public class LoginActivity extends BaseServerServlet {
	
	private static final long serialVersionUID = -7898242328656515639L;
	static Logger logger = LoggerFactory.getLogger(LoginActivity.class.getName());
	
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			int activityId = r.readInt();
			Map<Integer, SysActivity> map=getService.getAvailableActivitiesMap();
			SysActivity sa=map.get(activityId);
			int size = r.readInt();//max 10000
			for(int i=0;i<size;i++){
				int playerId=r.readInt();
				if(sa.getAction()==Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue()){
					updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue(), playerId,  sa.getStartTime(),0,0,null,null);
				}
			}
			return out.toByteArray();
		}catch (Exception e) {
			logger.warn("Error in LoginActivity: ", e);
			throw e;
		}
	}
}

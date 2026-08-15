package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

@SuppressWarnings("serial")
public class StageQuit extends BaseClientServlet{
	private static Logger logger = LoggerFactory.getLogger("stage");
	private static final String[] paramNames = {"rid","pid","channel_id","server_id"};
	
	
	@Override
	protected String innerService(String... args) {
		try{
			int rid = Integer.valueOf(args[0]);
			int pid = Integer.valueOf(args[1]);
			int channelId = Integer.valueOf(args[2]);
			int serverId = Integer.valueOf(args[3]);
			String result = mcc.get(CacheUtil.sStageQuit(rid, pid, channelId, serverId),Constants.CACHE_TIMEOUT);
			
			logger.debug("stage quit info ="+result);
			if(!StringUtil.isEmptyString(result)){
				return result;
			}else {
				//throw new Exception("stage quit data not exist. rid: " + rid + " cid :" + cid + " channelId:" + channelId);
				logger.warn("fail to get stage quit. rid:" + rid + " channelId:" + channelId + "cid:" + pid + " serverId:" + serverId);
				return Converter.warn(ExceptionMessage.GAME_OVER);
			}
		}catch (Exception e){
			logger.warn("error happend during get  stage clear. Exception is" + e );
			//return "error = \"fail to get stage clear data\"" ;
			return Converter.error(ExceptionMessage.OFF_CONNECT);
		}
	}


	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
	
}

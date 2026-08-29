package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

@SuppressWarnings("serial")
public class StageClear extends BaseClientServlet{
	private static Logger logger = LoggerFactory.getLogger("stage_clear");
	private static final String[] paramNames = {"rid","channel_id","server_id","pid"};

	@Override
	protected String innerService(String... args) {
		try{
			int rid = Integer.valueOf(args[0]);
			int channelId = Integer.valueOf(args[1]);
			int serverId = Integer.valueOf(args[2]);
			int playerId = Integer.valueOf(args[3]);
			Player player=getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			String brandOpenkey = Constants.STAGE_BRAND_OPEN_PREFIX + playerId;
			List<Integer> initList = new ArrayList<Integer>();
			mcc.set(brandOpenkey, Constants.BRAND_CACHE_ITEM_TIMEOUT, initList, Constants.CACHE_TIMEOUT);
			logger.debug("get stage clear result. rid :" + rid);
			String result = mcc.get(CacheUtil.sStageClear(rid, channelId, serverId),Constants.CACHE_TIMEOUT);
			if(!StringUtil.isEmptyString(result)){
//					String retStr = Converter.stageClearAppendOpenChances(playerId,result);
//					retStr = Converter.stageClearAppendGetScore(playerId, retStr);
//					retStr = Converter.stageClearAppendGetGps(playerId, retStr);
//					retStr = Converter.stageClearAppendGetExps(playerId, retStr);
					soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
					return result;
			}else {
				//throw new Exception("stage clear data not exist. rid: " + rid + " channelId:" + channelId);
				logger.warn("fail to get stage clear. rid:" + rid + " channelId:" + channelId + "serverId " + serverId );
				return Converter.warn(ExceptionMessage.GAME_OVER);
			}
		}catch (Exception e){
			logger.warn("error happend during  get stage clear. Exception is" + e );
			//return "error = \"fail to get stage clear data\"" ;
			return Converter.error(ExceptionMessage.OFF_CONNECT);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}

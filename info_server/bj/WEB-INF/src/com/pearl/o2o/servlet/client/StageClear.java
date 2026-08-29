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
//					mcc.delete(CacheUtil.sStageClear(rid, channelId, serverId));	
//					String retStr = Converter.stageClearAppendOpenChances(playerId,result);
//					retStr = Converter.stageClearAppendGetScore(playerId, retStr);
//					retStr = Converter.stageClearAppendGetGps(playerId, retStr);
//					retStr = Converter.stageClearAppendGetExps(playerId, retStr);
					soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
					return result;
			}else {
//				if(new Date().getTime()-timec > 20000 && 
//						mcc.get(CacheUtil.sStageClear(rid, channelId, serverId),Constants.CACHE_TIMEOUT)!=null){	//结算超时后，默认发送所有牌子前玩家可用翻牌数
				if(mcc.get(Constants.STAGE_OPEN_CHANCE_PREFIX + playerId)!=null){
					logger.info("send gift to player...");
//					mcc.delete(CacheUtil.sStageClear(rid, channelId, serverId));
					BaseClientServlet	bClientServlet2 = new StageClearOpenTimeOut();
					bClientServlet2.setCreateService(createService);
					bClientServlet2.setDeleteService(deleteService);
					bClientServlet2.setGetService(getService);
					bClientServlet2.setInfoLogger(infoLogger);
					bClientServlet2.setMcc(mcc);
					bClientServlet2.setMessageService(messageService);
					bClientServlet2.setNosqlService(nosqlService);
					bClientServlet2.setSoClient(soClient);
					bClientServlet2.setTeamService(teamService);
					bClientServlet2.setTransferDataToDc(transferDataToDc);
					bClientServlet2.setUpdateService(updateService);
					//如结算超时，反玩家翻牌数据
//					mcc.set(brandOpenkey, Constants.BRAND_CACHE_ITEM_TIMEOUT, new ArrayList<Integer>(), Constants.CACHE_TIMEOUT);
					bClientServlet2.service(new String[]{playerId+""});
					soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
				}
				
				logger.info("send gift to player over...");
					//更新角色数据
//					for(int playerId : playerIds){
//						mcc.set(Constants.STAGE_BRAND_OPEN_PREFIX + playerId, Constants.BRAND_CACHE_ITEM_TIMEOUT, new ArrayList<Integer>(), Constants.CACHE_TIMEOUT);
//						Player player=getService.getPlayerById(playerId);
//						soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
//					}
//				}
					
				//throw new Exception("stage clear data not exist. rid: " + rid + " channelId:" + channelId);
				logger.error("fail to get stage(Time out) clear. rid:" + rid + " channelId:" + channelId + "serverId " + serverId );
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

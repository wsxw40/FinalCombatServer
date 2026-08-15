package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.ServiceLocator;

public class EndNewServlet extends BaseServerServlet {
	private static final long serialVersionUID = -1402479052917824587L;
	static Logger log = LoggerFactory.getLogger(GetPlayer.class.getName());
	@Override
	protected byte[] innerService(BinaryReader r) throws IOException,
			Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			int playerId = r.readInt();
			int entertTime= r.readInt();
			int quitTime= r.readInt();
			int characterId= r.readInt();
			int isClear=r.readInt();
			Player player=getService.getPlayerById(playerId);
			if(ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()){
				ServiceLocator.nosqlService.addXunleiLog("1.7"+Constants.XUNLEI_LOG_DELIMITER+player.getUserName()
					+Constants.XUNLEI_LOG_DELIMITER+player.getName()+Constants.XUNLEI_LOG_DELIMITER
					+1+Constants.XUNLEI_LOG_DELIMITER
					+CommonUtil.simpleDateFormat.format(new Date(entertTime*1000l)));
			
				if(isClear!=0){
					ServiceLocator.nosqlService.addXunleiLog("1.7"+Constants.XUNLEI_LOG_DELIMITER+player.getUserName()
							+Constants.XUNLEI_LOG_DELIMITER+player.getName()+Constants.XUNLEI_LOG_DELIMITER
							+2+Constants.XUNLEI_LOG_DELIMITER
							+CommonUtil.simpleDateFormat.format(new Date(quitTime*1000l)));
				}else{
					ServiceLocator.nosqlService.addXunleiLog("1.7"+Constants.XUNLEI_LOG_DELIMITER+player.getUserName()
							+Constants.XUNLEI_LOG_DELIMITER+player.getName()+Constants.XUNLEI_LOG_DELIMITER
							+3+Constants.XUNLEI_LOG_DELIMITER
							+CommonUtil.simpleDateFormat.format(new Date(quitTime*1000l)));	
				}
				ServiceLocator.nosqlService.addXunleiLog("1.8"+Constants.XUNLEI_LOG_DELIMITER+player.getUserName()
						+Constants.XUNLEI_LOG_DELIMITER+player.getName()+Constants.XUNLEI_LOG_DELIMITER
						+characterId+Constants.XUNLEI_LOG_DELIMITER
						+CommonUtil.simpleDateFormat.format(new Date(entertTime*1000l)));
			}
			if(player.getIsNew()!=1&&isClear!=0){
				player.setIsNew(1);
				updateService.updatePlayerInfo(player);
				updateService.updatePlayerAchievementNotInStageClear(player, Constants.ACTION.NEWPLAYER.getValue(), 1);
				////成长任务：完成训练关
//				updateService.updatePlayerGrowthMission(player,GrowthMissionType.FINISH_NEW_PLAYER);
			}
			return out.toByteArray();
		}catch(Exception e){
			log.warn("Error in GetPlayer: ", e);
			throw e;
		}
	}
	@Override
	protected String getLockKey(BinaryChannelBuffer in) throws Exception {
		Integer playerId=in.readInt();
		return CommonUtil.getLockKey(playerId);
	}
}

package com.pearl.o2o.servlet.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.GeneralStageClearInfo;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.StageClearPlayerInfo;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.servlet.server.StageClear.MvpScore;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BiochemicalConstants;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.Constants.GAMETYPE;

@SuppressWarnings("serial")
public class StageQuit extends BaseServerServlet{
	private static int STAGECLEAR_EXPR = 600;
	private static Logger logger =LoggerFactory.getLogger("stage");
	
	@Override
	protected  byte[] innerService(BinaryReader r) throws Exception{

		try {
		GeneralStageClearInfo stageClearInfo = new GeneralStageClearInfo();
		int playerId =  r.readInt();
//		ServiceLocator.stageLogger.error("quit playerId="+playerId);
		stageClearInfo.setClear(false);
		
		int rid =  r.readInt();
		stageClearInfo.setRid(rid);
		
		int serverId = r.readInt();
		stageClearInfo.setServerId(serverId);
		
		int channelId = r.readInt();
		stageClearInfo.setChannelId(channelId);
		int hostId = r.readInt();
		stageClearInfo.setHostId(hostId);
		
		int type = r.readByte();
		stageClearInfo.setType(type);
		int gameStart = r.readInt();
		stageClearInfo.setGameStart(new Date(gameStart*1000l));
		int gameEnd = r.readInt();
		stageClearInfo.setGameEnd(new Date(gameEnd*1000l));
		int levelId = r.readInt();
		stageClearInfo.setLevelId(levelId);
		
		int isTeam = r.readByte();
		stageClearInfo.setIsTeam(isTeam);
		int teamaId=0;
		int teambId=0;
		if(isTeam!=0){
			 teamaId = r.readInt();
			 teambId = r.readInt();
		}
		int winner = r.readByte();
//		logger.error("winner="+winner);
		stageClearInfo.setWinner(winner);
		if(type==Constants.GAMETYPE.TEAMZYZDZ.getValue()){
			
			String bodygameKey=r.readString();
			if(!bodygameKey.equals("")){//  非  挑战模式
				String gameKey=Constants.TeamSpaceConstants.BATTLEFIELD_START_PREFIX+bodygameKey;
				Integer totalRes=mcc.get(gameKey, Constants.CACHE_TIMEOUT);
				mcc.delete(gameKey);
				stageClearInfo.setTotalRes(Float.valueOf(totalRes));
			}else{
				stageClearInfo.setTotalRes(-1);
			}
			//战队资源战模式基地还剩血量
			float damagePercent=r.readFloat();
			stageClearInfo.setDamagePercent(1-damagePercent);	
			
		}else{
			int terroristScore = r.readInt();
			stageClearInfo.setTerroristScore(terroristScore);
			int	policeScore = r.readInt();
			stageClearInfo.setPoliceScore(policeScore);
		}
//		logger.error("quit playerId="+playerId +" type="+type+" hostId="+hostId);
		int mvpId=r.readInt();
		
		int	firstKill = r.readInt();
		int firstDead = r.readInt();
		stageClearInfo.setFirstKill(firstKill);
		stageClearInfo.setFirstDead(firstDead);
		
		int itemType = r.readByte();
		if(itemType != 0){
//			int itemValue = r.readInt();
//			String target = r.readString();
//			String user = r.readString();
//			stageClearInfo.addRoomItem(itemType, itemValue, target, user);
		}
		//player list
		int playerArrayLength = r.readInt();
		List<StageClearPlayerInfo> playerInfoListTeamAll = new ArrayList<StageClearPlayerInfo>();//team0
		List<StageClearPlayerInfo> playerInfoListTeam0 = new ArrayList<StageClearPlayerInfo>();//team0
		List<StageClearPlayerInfo> playerInfoListTeam1 = new ArrayList<StageClearPlayerInfo>();//team1
		List<MvpScore> mvpScores = new ArrayList<MvpScore>();//team1
		MvpScore mvpScore=null;
		stageClearInfo.setStageClearPlayerInfoAll(playerInfoListTeamAll);
		stageClearInfo.setStageClearPlayerInfoTeam0(playerInfoListTeam0);
		stageClearInfo.setStageClearPlayerInfoTeam1(playerInfoListTeam1);
//		int selectId=0;
		for (int i=0; i<playerArrayLength; i++) {
			StageClearPlayerInfo playerInfo = new StageClearPlayerInfo();
			
			int id = r.readInt();
			playerInfo.setId(id);
			
			String characterName = r.readString();
			playerInfo.setName(characterName);
			
			byte side = r.readByte();
			playerInfo.setSide(side);
			
//			Player player=getService.getPlayerById(playerId);
//			if(playerInfo.getSide() == Constants.SIDE_OBSERVER){//observer don't need update
//				playerInfo.setNeedUpdate(false);
//			}else if(player.getIsVip()==0){
//				playerInfo.setNeedUpdate(false);
//			}else{
//				playerInfo.setNeedUpdate(true);
//			}
			
			int onlineMinutes = r.readInt();
			playerInfo.setOnlineMinutes(onlineMinutes);
			
			int offlineMinutes = r.readInt();
			playerInfo.setOfflineMinutes(offlineMinutes);
			
			byte decrNum = r.readByte();
			nosqlService.setPlayerRemainVoterNum(id, decrNum);
			int remainReliveCoinNum = r.readInt();
			List<PlayerItem> reliveCoinList = getService.getPlayerItemByItemIid(id, Constants.DEFAULT_ITEM_TYPE, Constants.SPECIAL_ITEM_IIDS.RELIVE_COIN.getValue());
			int totalReliveCoinNum = 0;
			for(PlayerItem pi : reliveCoinList){
				if(pi.getQuantity()!=0){
					totalReliveCoinNum+=pi.getQuantity();
				}
			}
			int costReliveCoinNum = totalReliveCoinNum - remainReliveCoinNum;
			if(costReliveCoinNum>0){
			if(remainReliveCoinNum==0){
				for(PlayerItem pi : reliveCoinList){
					int quantity = pi.getQuantity();
					if(quantity==0)
						continue;
					createService.updateItemQuantity(pi,quantity);
					deleteService.deletePlayerItemInMemcached(id, getService.getSysItemByItemId(pi.getItemId()));
				}
			}else{
				for(PlayerItem pi : reliveCoinList){
					int quantity = pi.getQuantity();
					if(quantity==0)
						continue;
					if(quantity<costReliveCoinNum){
						createService.updateItemQuantity(pi,quantity);
						costReliveCoinNum-=quantity;
						deleteService.deletePlayerItemInMemcached(id, getService.getSysItemByItemId(pi.getItemId()));
					}else{
						createService.updateItemQuantity(pi,costReliveCoinNum);
						costReliveCoinNum = 0;
						deleteService.deletePlayerItemInMemcached(id, getService.getSysItemByItemId(pi.getItemId()));
						break;
					}
				}
			}
			}
			
//			logger.info("stageQuit :id " + playerInfo.getId() + " online" + onlineMinutes + " offline " + offlineMinutes);
			
			int start = r.readInt();
			int end = r.readInt();
			playerInfo.setStart(new Date(start*1000l));
			playerInfo.setEnd(new Date(end*1000l));
			int score = r.readInt();
			playerInfo.setScore(score);
//			ServiceLocator.stageLogger.info("stageQuit "+playerInfo.getName()+ " get scoreScore"+score+"playerInfo ="+playerInfo.getStart()+" "+playerInfo.getEnd());
			short kill = r.readShort();
			playerInfo.setKill(kill);
			short dead = r.readShort();
			playerInfo.setDead(dead);
			short controlNum = r.readShort();
			playerInfo.setControlNum(controlNum);
			short revengeNum = r.readShort();
			playerInfo.setRevengeNum(revengeNum);
			short assistNum = r.readShort();
			playerInfo.setAssistNum(assistNum);
			short knifeKill = r.readShort();
			playerInfo.setKnifeKill(knifeKill);
			
			int maxHeadshotNum = r.readInt();
			playerInfo.setMaxHeadshotNum(maxHeadshotNum);
			int maxHeadshotCharacter = r.readInt();
			playerInfo.setMaxHeadshotCharacter(maxHeadshotCharacter);
			if(mvpId==id&&maxHeadshotNum!=0){
				 mvpScore=new MvpScore();
				mvpScore.setScore(maxHeadshotNum*160);
				mvpScore.setCharacterId(maxHeadshotCharacter);
				mvpScores.add(mvpScore);
			}
			
			int maxKillNum = r.readInt();
			playerInfo.setMaxKillNum(maxKillNum);
			int maxKillNumCharacter = r.readInt();
			playerInfo.setMaxKillNumCharacter(maxKillNumCharacter);
			if(mvpId==id&&maxKillNum!=0){
				 mvpScore=new MvpScore();
				 int score2=135*maxKillNum;
				 for(int m=1;m<maxKillNum;m++){
					 score2+=5;
				 }
				mvpScore.setScore(score2);
				mvpScore.setCharacterId(maxKillNumCharacter);
				mvpScores.add(mvpScore);
			}
			
			int maxHealthNum = r.readInt();
			playerInfo.setMaxHealthNum(maxHealthNum);
			int maxHealthNumCharacter = r.readInt();
			playerInfo.setMaxHealthNumCharacter(maxHealthNumCharacter);
			if(mvpId==id&&maxHealthNum!=0){
				 mvpScore=new MvpScore();
				mvpScore.setScore(maxHealthNum*15);
				mvpScore.setCharacterId(maxHealthNumCharacter);
				mvpScores.add(mvpScore);
			}
			
			int maxDamageNum = r.readInt();
			playerInfo.setMaxDamageNum(maxDamageNum);
			int maxDamageNumCharacter = r.readInt();
			playerInfo.setMaxDamageNumCharacter(maxDamageNumCharacter);
			int maxLiveTime = r.readInt();
			playerInfo.setMaxLiveTime(maxLiveTime);
			int maxLiveTimeCharacter = r.readInt();
			playerInfo.setMaxLiveTimeCharacter(maxLiveTimeCharacter);
			if(mvpId==id){
				mvpScore=new MvpScore();
				mvpScore.setScore(0);
				mvpScore.setCharacterId(maxLiveTimeCharacter);
				mvpScores.add(mvpScore);
			}
			
			short bulletNum = r.readShort();
			short bottleHpNum = r.readShort();
			playerInfo.setBottleHpNum(bottleHpNum);
			playerInfo.setBulletNum(bulletNum);
			int characterDatasize = r.readInt();
			
			for (int j=0;j<characterDatasize;j++){
				int characterId = r.readInt();
				int characterKill = r.readShort();
				int characterDead = r.readShort();
				int characterControlNum = r.readShort();
				int characterRevengeNum = r.readShort();
				int characterAssistNum = r.readShort();
				int characterGrenadeKill = r.readShort();
				int characterKnifeKill = r.readShort();
//				ServiceLocator.stageLogger.error("characterKnifeKill="+characterKnifeKill);
				int characterUsed = r.readShort();
//				if(characterUsed>0){
//					selectId=characterId;
//				}
				int characterHeadshot = r.readShort();
				int characterMaxDamage = r.readShort();
				int characterBoostKill = r.readShort();
				int characterSustainKill = r.readShort();
				int characterHealthNum=r.readInt();
				if(characterHealthNum>0&&characterId==6){
					playerInfo.setHealth(characterHealthNum);
				}
				//else{
			//		logger.warn("characterId="+characterId+" characterHealthNum="+characterHealthNum);
		//		}
//				ServiceLocator.stageLogger.error("characterUsed="+characterUsed);
				playerInfo.addSingleCharacterInfo(characterId, characterKill, characterDead, 
						characterControlNum,characterRevengeNum,characterAssistNum, characterKnifeKill, characterUsed,
						characterGrenadeKill, characterHeadshot, characterMaxDamage, characterBoostKill, characterSustainKill,characterHealthNum);
			}
			int killSize=r.readInt();
			for(int k=0;k<killSize;k++){
				int characterId = r.readInt();
				if(characterId==Constants.DEFAULT_BOSS_CHARACTER_ID){
					int num = r.readInt();
					updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.KILL_BOSS_ACTIVITY.getValue(),
							playerInfo.getId(), null, num, 0,null,null);
				}else if(characterId==BiochemicalConstants.ordinaryCId||characterId==BiochemicalConstants.especialCId){
					int num = r.readInt();
					updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.KILL_BIOCHEMICAL.getValue(),
							playerInfo.getId(), null, num, 0,null,null);
				}else if(characterId>10){
//					logger.warn("stage clear characterId out of range=="+characterId);
					int num = r.readInt();
//					logger.warn("num="+num);
					continue;
				}else{
					int num = r.readInt();
					if(characterId>6){
						logger.debug("stage clear characterId out of range=="+characterId);
						logger.debug("num="+num);
					}
					playerInfo.getKillCharacter()[characterId]=num;
				}
			}
			int size = r.readInt();
			List<OnlineAward> boss2Awards = new ArrayList<OnlineAward>();
			for(int j =0;j<size;j++){
				int sysItemId = r.readInt();//物品ID
				int num = r.readInt();//物品数量
//				SysItem si = getService.getSysItemByItemId(sysItemId);
//				if(si!=null){
//					createService.sendItem(si, getService.getPlayerById(id),new Payment(num,1), "N", "Y", "N");
//					ServiceLocator.stageClearLog.info("Boss2/Award:\t"+id+"\t"+si.getDisplayNameCN()+"\t"+num+"\t"+1);
//					OnlineAward oa = new OnlineAward();
//					oa.setSysItem(si);
//					oa.setUnit(num);
//					oa.setUnitType(1);
//					boss2Awards.add(oa);
//				}else{
//					logger.warn("Boss2/SysItemNull:\t"+id+"\t"+sysItemId+"\t"+num);
//				}
			}
			playerInfo.setBoss2Awards(boss2Awards);
			int level = r.readInt();
			playerInfo.setPassLevel(level);
			if(type==GAMETYPE.TEAMZYZDZ.getValue()){
				short zyzdzItemSize=r.readShort();
				for(int count=0;count<zyzdzItemSize;count++){
					int sysItemId=r.readInt();
					int costItemNum=r.readShort();
					PlayerItem playerItem=new PlayerItem();
					playerItem.setItemId(sysItemId);
					playerItem.setSysItem(getService.getSysItemByItemId(sysItemId));
					playerItem.setQuantity(costItemNum);
					playerInfo.getZyzdzCostItem().add(playerItem);
					List<PlayerItem> playerItems=getService.getPlayerItemListBySysItemId(id, Constants.DEFAULT_ZYZDZ_TYPE, sysItemId);
					if(costItemNum>0){
						for(PlayerItem pi : playerItems){
							int quantity = pi.getQuantityForZYZDZPersonal();
							if(quantity==0)
								continue;
							if(quantity<costItemNum){
								createService.updateItemQuantity(pi,quantity);
								costItemNum-=quantity;
								deleteService.deletePlayerItemInMemcached(id, getService.getSysItemByItemId(pi.getItemId()));
							}else{
								createService.updateItemQuantity(pi,costItemNum);
								costItemNum = 0;
								deleteService.deletePlayerItemInMemcached(id, getService.getSysItemByItemId(pi.getItemId()));
								break;
							}
						}	
					}
				}
			}
			if(id!=playerId){
				continue;
			}
			logger.debug("stageQuit :id " + playerInfo.getId() + " online" + onlineMinutes + " offline " + offlineMinutes);
			if (playerInfo.getSide() != Constants.SIDE_OBSERVER){
				if(playerInfo.getSide()==Constants.SIDE_RED){
					playerInfoListTeam0.add(playerInfo);
					playerInfoListTeamAll.add(playerInfo);
				}else if(playerInfo.getSide()==Constants.SIDE_BLUE){
					playerInfoListTeam1.add(playerInfo);
					playerInfoListTeamAll.add(playerInfo);
				}
			}
			
		}
//		if(type==Constants.GAMETYPE.NEWTRAIN.getValue()){
//			Player player=getService.getSimplePlayerById(playerId);
//			ServiceLocator.nosqlService.addXunleiLog("1.7"+Constants.XUNLEI_LOG_DELIMITER+player.getUserName()
//					+Constants.XUNLEI_LOG_DELIMITER+player.getName()+Constants.XUNLEI_LOG_DELIMITER
//					+1+Constants.XUNLEI_LOG_DELIMITER
//					+CommonUtil.simpleDateFormat.format(new Date(gameStart*1000l)));
//			ServiceLocator.nosqlService.addXunleiLog("1.7"+Constants.XUNLEI_LOG_DELIMITER+player.getUserName()
//					+Constants.XUNLEI_LOG_DELIMITER+player.getName()+Constants.XUNLEI_LOG_DELIMITER
//					+3+Constants.XUNLEI_LOG_DELIMITER
//					+CommonUtil.simpleDateFormat.format(new Date(gameEnd*1000l)));
//			ServiceLocator.nosqlService.addXunleiLog("1.8"+Constants.XUNLEI_LOG_DELIMITER+player.getUserName()
//					+Constants.XUNLEI_LOG_DELIMITER+player.getName()+Constants.XUNLEI_LOG_DELIMITER
//					+selectId+Constants.XUNLEI_LOG_DELIMITER
//					+CommonUtil.simpleDateFormat.format(new Date(gameStart*1000l)));
//		}
		if(stageClearInfo.getStageClearPlayerInfoAll().size()!=0){
			if(type!=Constants.GAMETYPE.TEAMZYZDZ.getValue()) {
				updateService.updatePlayerStageQuit(playerId,stageClearInfo);
			}

			String result = Converter.stageQuit(stageClearInfo);
			String key = CacheUtil.sStageQuit(stageClearInfo.getRid(),playerId,stageClearInfo.getChannelId(), stageClearInfo.getServerId());
			mcc.set(key,STAGECLEAR_EXPR, result,Constants.CACHE_TIMEOUT);
		}
		return Constants.EMPTY_BYTE_ARRAY; 
		}catch (BaseException e) {
			logger.debug("normal player stage quit,do nothing");
		} 
		catch (Exception e) {
			logger.warn("Error happend when processing the stage quit. Exception is " , e);
		}
		return Constants.EMPTY_BYTE_ARRAY;
	
	}

	class  MvpScore{
		int score;
		int characterId;
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		public int getCharacterId() {
			return characterId;
		}
		public void setCharacterId(int characterId) {
			this.characterId = characterId;
		}
	}
}

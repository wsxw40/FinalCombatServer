package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.GunProperty;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.TmpPlayerItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.StringUtil;

public class ShootingAndLook extends BaseClientServlet {
	
	private static final long serialVersionUID = 4169525300827703426L;
	private Logger logger = LoggerFactory.getLogger("shooting");
	private static final String[] paramNames = {"pid","type","action_type","index"};
	@Override
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int type = StringUtil.toInt(args[1]);
			int action = StringUtil.toInt(args[2]);
			int index = StringUtil.toInt(args[3]);
			
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_CHARACTER);
			if(index<0||index>=Constants.SHOOTING_BOTTLE_NUM){
				logger.warn("ShootingAndLook/" + Constants.RPC_PARAM_ERROR_LOG + ":\tindex="+index);
				return Converter.error(ExceptionMessage.PARAM_ERROR_MSG);
			}
			String shootMccKey = Constants.SHOOTING_MCC_KEY_PREFX + playerId;
			String shootValueStr = mcc.get(shootMccKey);
			
			Integer boutNum = 1;//回合数
			Integer dartleNum = 0;//连射数
			Integer awardLevel = 0;//连射奖励级别
			Integer nextShootFree = 0;//下次是否免费
			if(shootValueStr!=null){
				String[] shootNums = shootValueStr.split(":");
				if(shootNums.length==4){
					boutNum = StringUtil.toInt(shootNums[0]);
					dartleNum = StringUtil.toInt(shootNums[1]);
					awardLevel = StringUtil.toInt(shootNums[2]);
					nextShootFree = StringUtil.toInt(shootNums[3]);
				}
			}
			List<Integer> randomGiftsIdList = mcc.get(Constants.SHOOT_RDM_GIFTS_ID_MCC_KEY_PRFX + playerId);//用于存放每个靶的奖励，如果偷看了，将该id存进去，没有则默认为0
			if(randomGiftsIdList==null||randomGiftsIdList.size()!=Constants.SHOOTING_BOTTLE_NUM){//如果格式不对，重新初始化
				randomGiftsIdList = Arrays.asList(0,0,0,0,0,0,0);
			}
			List<PlayerItem> myAmmos = getService.getPlayerItemsByIIdAndIValue(playerId, Constants.DEFAULT_ITEM_TYPE, Constants.SPECIAL_ITEM_IIDS.SHOOTING_AMMO.getValue(), type);
			int ammoNum = 0;
			for(PlayerItem pi : myAmmos){
				ammoNum += pi.getQuantity();
			}
			int lookNum = 0;
			List<PlayerItem> lookItems = getService.getPlayerItemsByIIdAndIValue(playerId, Constants.DEFAULT_ITEM_TYPE, Constants.SPECIAL_ITEM_IIDS.SHOOTING_LOOK.getValue(),type);
			for(PlayerItem pi : lookItems){
				lookNum += pi.getQuantity();
			}
			List<OnlineAward> awardsPool = getAwardListByTypeAction(type, action);//获取当前靶场以及操作的奖励池
			List<OnlineAward> retGifts = new ArrayList<OnlineAward>();//用于返回的奖励列表
			if(action==1){//射击
				if(ammoNum<1&&nextShootFree==0){
					return Converter.warn(ExceptionMessage.NOT_ENOUGH_AMMO);
				}
				OnlineAward  oa = new OnlineAward();
				SysItem si = null;
				int oaId = randomGiftsIdList.get(index);
				if(oaId!=0){
					oa = getService.getOnlineAwardById(oaId);
				}else{
					oa = getService.randomOnlineAwardFromList(1, awardsPool).get(0);
					randomGiftsIdList.set(index, oa.getId());
				}
				si = getService.getSysItemByItemId(oa.getItemId());
				for(int i = 0;i<Constants.SHOOTING_BOTTLE_NUM;i++){//遍历，如果是0则随机生成
					if(randomGiftsIdList.get(i)==0){
						Integer giftId = getService.randomOnlineAward(getAwardListByTypeAction(type, 0)).getId();
						randomGiftsIdList.set(i, giftId);
					}
				}
				retGifts = getService.getOnlineAwardsByTypeAndIds(Constants.ONLINE_AWARD_TYPES.SHOOTING_AWARD.getValue(), randomGiftsIdList);
				if(si!=null){
					oa.setSysItem(si);
					if(nextShootFree==0){//本次是否免费
						for(PlayerItem pi :myAmmos){
							if(pi.getQuantity()>=1){
								ammoNum --;
								updateService.updateItemQuantity(pi, -1);
								deleteService.deletePlayerItemInMemcached(playerId, getService.getSysItemByItemId(pi.getItemId()));
								break;
							}
						}
					}else{
						nextShootFree=0;
					}
					if(si.getIId()==Constants.SPECIAL_ITEM_IIDS.DARTLE_ADD.getValue()){
						dartleNum+= StringUtil.toInt(si.getIValue());
					}else if(si.getIId()==Constants.SPECIAL_ITEM_IIDS.NULL_BOTTLE.getValue()){
						dartleNum=-1;
						
					}else if(si.getIId()==Constants.SPECIAL_ITEM_IIDS.NEXT_SHOOT_FREE.getValue()){
						nextShootFree=1;
					}else{
						if(si.getIId()==Constants.SPECIAL_ITEM_IIDS.SHOOTING_AMMO.getValue()&&si.getIValue().equals(String.valueOf(type))){//如果是相应子弹则加上子弹数量
							ammoNum += oa.getUnit();
						}
						createService.awardToPlayer(player, si, new Payment(oa.getUnit(),oa.getUnitType()), null, "Y");
					}
					if(si.getRareLevel()>=Constants.SHOOT_HIGH_RARE_LEVEL[type]){//稀有物品加入靶场奖励列表，并发公告
						nosqlService.addDartleAwardToTop(playerId, oa.getId());
						int color = si.getIsStrengthen() == 1 ? si.getStrengthLevel() : 0;
						String shootName = type==0?CommonMsg.SHOOT_SILVER_DLB_MSG:CommonMsg.SHOOT_GOLD_DLB_MSG;
						String shootStr = type==0?CommonMsg.SHOOT_SILVER_DLB_MSG1:CommonMsg.SHOOT_GOLD_DLB_MSG1;
						soClient.proxyBroadCast(Constants.MSG_NBA, Constants.SYSTEM_SPEAKER, 
								CommonUtil.messageFormatI18N(CommonMsg.SHOOT_AWEARD_SYS, new Object[]{GunProperty.RED + "@!" + player.getName()+"@!"+GunProperty.RED + "@!" + player.getId(),shootName ,shootStr,color + "@!" + si.getDisplayName(),oa.getUnit()}));
					}
					boutNum++;
					dartleNum++;
					logger.info("ShootingAndLook/ShootAward:\t" +playerId+"\t" +si.getDisplayNameCN() + "\t" +oa.getUnit()+ "\t" + oa.getUnitType());
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
						nosqlService.addXunleiLog("11.2"
								+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
								+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
								+ Constants.XUNLEI_LOG_DELIMITER + type
								+ Constants.XUNLEI_LOG_DELIMITER + si.getId()
								+ Constants.XUNLEI_LOG_DELIMITER + si.getDisplayNameCN()
								+ Constants.XUNLEI_LOG_DELIMITER + oa.getUnit()
								+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())
								);
					}
				}else{
					logger.warn("ShootingAndLook/SysItemNull:\t" + oa.getItemId() + "\t" + oa.getId());
				}
				
				for(OnlineAward gift : retGifts){
					SysItem gsi = getService.getSysItemByItemId(gift.getItemId());
					if(gsi!=null){
						gift.setSysItem(gsi);
					}else{
						logger.warn("ShootingAndLook/SysItemNull:\t" + oa.getItemId() + "\t" + oa.getId());
					}
				}
				mcc.delete(Constants.SHOOT_RDM_GIFTS_ID_MCC_KEY_PRFX + playerId);
				updateService.updatePlayerGrowthMission(player, GrowthMissionType.SHOOTING);
				
			}else if(action ==2){//偷看
				if(lookNum<1){
					return Converter.warn(ExceptionMessage.NOT_ENOUGH_LOOK);
				}
				OnlineAward oa =  getService.randomOnlineAwardFromList(1, awardsPool).get(0);
				randomGiftsIdList.set(index, oa.getId());
				mcc.set(Constants.SHOOT_RDM_GIFTS_ID_MCC_KEY_PRFX + playerId,  Constants.CACHE_TIMEOUT_DAY,randomGiftsIdList);
				SysItem si = getService.getSysItemByItemId(oa.getItemId());
				if(si!=null){
					oa.setSysItem(si);
					for(PlayerItem pi :lookItems){
						if(pi.getQuantity()>=1){
							lookNum --;
							updateService.updateItemQuantity(pi, -1);
							deleteService.deletePlayerItemInMemcached(playerId, getService.getSysItemByItemId(pi.getItemId()));
							break;
						}
					}
				}else{
					logger.warn("ShootingAndLook/SysItemNull:\t" + oa.getItemId() + "\t" + oa.getId());
				}
				retGifts.add(oa);
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					nosqlService.addXunleiLog("11.1"
							+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + type
							+ Constants.XUNLEI_LOG_DELIMITER+ 1
							+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
							);
				}
			}else if(action ==3){//全部偷看
				if(lookNum<Constants.LOOK_ALL_COST_NUM){
					return Converter.warn(ExceptionMessage.NOT_ENOUGH_LOOK);
				}
				randomGiftsIdList = randomGiftsIdList(playerId,type,action);
				retGifts = getService.getOnlineAwardsByTypeAndIds(Constants.ONLINE_AWARD_TYPES.SHOOTING_AWARD.getValue(), randomGiftsIdList);
				int totalCost = Constants.LOOK_ALL_COST_NUM;//全部偷看消耗
				for(OnlineAward oa : retGifts){
					SysItem si = getService.getSysItemByItemId(oa.getItemId());
					if(si!=null){
						oa.setSysItem(si);
					}else{
						totalCost--;
						logger.warn("ShootingAndLook/SysItemNull:\t" + oa.getItemId() + "\t" + oa.getId());
					}
				}
				lookNum -=totalCost;
				for(PlayerItem pi :lookItems){
					int quantity = pi.getQuantity();
					if(quantity>=totalCost){
						updateService.updateItemQuantity(pi, -totalCost);
						deleteService.deletePlayerItemInMemcached(playerId, getService.getSysItemByItemId(pi.getItemId()));
						break;
					}else if(quantity>0){
						totalCost-=quantity;
						updateService.updateItemQuantity(pi, -quantity);
					}
				}
				updateService.updatePlayerGrowthMission(player, GrowthMissionType.SHOOTING_LOOK);
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					nosqlService.addXunleiLog("11.1"
							+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + type
							+ Constants.XUNLEI_LOG_DELIMITER+ 5
							+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
							);
				}
			}else if(action ==4){//刷新
				boutNum++;
				mcc.delete(Constants.SHOOT_RDM_GIFTS_ID_MCC_KEY_PRFX + playerId);
			}else{
				logger.warn("ShootingAndLook/" + Constants.RPC_PARAM_ERROR_LOG + ":\taction="+action);
				return Converter.error(ExceptionMessage.PARAM_ERROR_MSG);
			}
			
			int dartleLevel = 0;
			for(int num : Constants.DARTLE_LEVEL_NUMS){
				if(dartleNum>=num){
					dartleLevel++;
				}else{
					break;
				}
			}
			int dartleAwardType = type==0?Constants.ONLINE_AWARD_TYPES.SILVER_DARTLE_AWARD.getValue():Constants.ONLINE_AWARD_TYPES.GOLD_DARTLE_AWARD.getValue();
			if(dartleLevel>awardLevel){
				List<OnlineAward> dartleAwards = getService.getOnlineAwardByTypeAndLevel(dartleAwardType, dartleLevel-1);
				if(dartleAwards!=null&&dartleAwards.size()>0){
					OnlineAward dartleAward = dartleAwards.get(0);
					SysItem si = getService.getSysItemByItemId(dartleAward.getItemId());
					if(si!=null){
						createService.awardToPlayer(player, si, new Payment(dartleAward.getUnit(),dartleAward.getUnitType()), null, "Y");
						if(si.getIId()==Constants.SPECIAL_ITEM_IIDS.SHOOTING_AMMO.getValue()&&si.getIValue().equals(String.valueOf(type))){//如果奖励是子弹则加上相应数量
							ammoNum += dartleAward.getUnit();
						}
						logger.info("ShootingAndLook/DartleAward:\t" + playerId+"\t"+si.getDisplayNameCN() + "\t" +dartleAward.getUnit()+ "\t" + dartleAward.getUnitType());
						awardLevel =dartleLevel;
					}else{
						logger.warn("ShootingAndLook/SysItemNull:\t" + dartleAward.getItemId() + "\t" + dartleAward.getId());
					}
				}
			}
			if(dartleLevel>=Constants.DARTLE_LEVEL_NUMS.length){
				dartleLevel = Constants.DARTLE_LEVEL_NUMS.length-1;
			}
			int needDartleNum = Constants.DARTLE_LEVEL_NUMS[dartleLevel];
			List<OnlineAward> dartleGifts = getService.getOnlineAwardByTypeAndLevel(dartleAwardType, dartleLevel);
			OnlineAward dartleGift = null;
			if(dartleGifts!=null&&dartleGifts.size()>0){
				dartleGift = dartleGifts.get(0);
				SysItem si = getService.getSysItemByItemId(dartleGift.getItemId());
				if(si!=null){
					dartleGift.setSysItem(si.clone());
				}
			}
			List<String> dartleTopList = getDartleTopList(playerId,action,dartleNum);
			List<TmpPlayerItem> dartleAwards = nosqlService.getDartleAwardsTopList();
			String newShootValueStr = boutNum+":"+dartleNum+":"+awardLevel+":"+nextShootFree;
			mcc.set(shootMccKey, Constants.CACHE_TIMEOUT_DAY, newShootValueStr);
			return Converter.shootingLook(boutNum, dartleNum, ammoNum,lookNum,needDartleNum, retGifts,dartleGift, dartleTopList,dartleAwards);
			
		} catch (BaseException e) {
			logger.warn("ShootingAndLook/Warn:\t",e);
			return Converter.warn(e.getMessage());
		} catch (Exception e){
			logger.error("ShootingAndLook/Error:\t", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
	/**
	 * 靶场奖励物品ID列表存入memcache
	 * @param playerId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private List<Integer> randomGiftsIdList(int playerId ,int type,int action) throws Exception{
		List<Integer> randomGiftsIdList = new ArrayList<Integer>();
		List<OnlineAward> allShootGifts = getAwardListByTypeAction(type, action);
		List<OnlineAward> randomGifts = getService.randomOnlineAwardFromList(Constants.SHOOTING_BOTTLE_NUM, allShootGifts);
		for(OnlineAward oa : randomGifts){
			randomGiftsIdList.add(oa.getId());
		}
		Collections.shuffle(randomGiftsIdList);
		if(action<4&&action>1){
			mcc.set(Constants.SHOOT_RDM_GIFTS_ID_MCC_KEY_PRFX + playerId, Constants.CACHE_TIMEOUT_DAY, randomGiftsIdList);
		}	
		return randomGiftsIdList;
	}
	/**
	 * 根据玩家是否偷看，靶场类型，生成奖励池
	 * @param type
	 * @param action
	 * @return
	 * @throws Exception
	 */
	private List<OnlineAward> getAwardListByTypeAction(int type,int action) throws Exception{
		List<OnlineAward> typeShootGifts = new ArrayList<OnlineAward>();
		if(type==0){
			if(action ==1){
				typeShootGifts = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.SHOOTING_AWARD.getValue(),1);
			}else if(action==2||action==3){
				typeShootGifts = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.SHOOTING_AWARD.getValue(),3);
			}else{
				typeShootGifts = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.SHOOTING_AWARD.getValue(),5);
			}
			
		}else if(type==1){
			if(action ==1){
				typeShootGifts = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.SHOOTING_AWARD.getValue(),2);
			}else if(action==2||action==3){
				typeShootGifts = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.SHOOTING_AWARD.getValue(),4);
			}else{
				typeShootGifts = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.SHOOTING_AWARD.getValue(),6);
			}
		}else{
			logger.warn("ShootingAndLook/" + Constants.RPC_PARAM_ERROR_LOG + ":\ttype="+type);
			throw new BaseException(ExceptionMessage.PARAM_ERROR_MSG);
		}
		return typeShootGifts;
	}
	/**
	 * 连射排行
	 * @param playerId
	 * @param action
	 * @param dartleNum
	 * @return
	 * @throws Exception
	 */
	private List<String> getDartleTopList(int playerId, int action ,int dartleNum) throws Exception{
		String dartleTopRedisKey = Constants.DARTLE_TOP_REDIS_KEY;
		NoSql nosql = nosqlService.getNosql();
		if(action==1){//射击更新玩家连射排行榜
			int topTotal = (int) nosql.zCard(dartleTopRedisKey);
			double myValue = Math.abs(nosql.zScore(dartleTopRedisKey, String.valueOf(playerId)));
			if(dartleNum > myValue){
				if(topTotal>=Constants.DARTLE_TOP_NUM){
					double lastValue = Math.abs(nosql.zrangeWithScores(dartleTopRedisKey, -1, -1).iterator().next().getScore());
					if(dartleNum > lastValue){
						nosql.zAdd(dartleTopRedisKey, -dartleNum, String.valueOf(playerId));
					}
				}else{
					nosql.zAdd(dartleTopRedisKey, -dartleNum, String.valueOf(playerId));
				}
			}
		}
		List<String> dartleTopList = new ArrayList<String>();
		Set<String> rankSet= nosql.zRange(dartleTopRedisKey, 0, Constants.DARTLE_TOP_NUM-1);
		for(String s :rankSet){
			Player p= getService.getPlayerById(Integer.parseInt(s));
			if(p!=null){
				String newStr = CommonUtil.generateDartleTopRetStr(dartleTopRedisKey,p);
				dartleTopList.add(newStr);
			}else{
				logger.warn("GetPlayerCommonTop/PlayerNull:\t" + s);
				nosql.zRem(dartleTopRedisKey,s);
			}
			
		}
		return dartleTopList;
	}
}

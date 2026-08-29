package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.GunProperty;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.XunleiLogUtils;

public class VipRandomStart extends BaseClientServlet {

	private static final long serialVersionUID = 1490709502989159783L;
	private static Logger log = LoggerFactory.getLogger("vipsafecabinet");
	private static final String[] paramNames = { "pid","playeritemid"};
	
	protected String innerService(String... args) {
		try {
			String result = "";
			int playerId = StringUtil.toInt(args[0]);
			int playerItemId = StringUtil.toInt(args[1]);
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			if(player.getIsVip()==0){
				throw new BaseException(ExceptionMessage.PLAYER_NOT_VIP);
			}
			PlayerItem playerVip = getService.getPlayerItemByItemId(playerId,Constants.DEFAULT_OPEN_TYPE, playerItemId);
			
			//获得系统随机开启的vip宝箱物品
			String randomStartIndexs = nosqlService.getRandomStartIndexs(playerId);
			String[] indexsArr = randomStartIndexs.split(":");
			if(indexsArr==null||indexsArr.length<=0){
//				log.error("player:" +playerId+Constants.VIP_START_INDEX_REDIS+Constants.NO_VALUE_FIND_FROM_REDIS);
				log.error("VipRandomStart/NoIndexsInRedis:\t" +Constants.VIP_RANDOM_START_LIST_PREFIX + playerId);
				throw new BaseException(ExceptionMessage.NO_VIP_RANDOM_INDEXS);
			}
			int time = Constants.VIP_OPEN_CHANCE_NUM - indexsArr.length;
			int needVip = Constants.VIP_START_COSTS[time];
			int playerVipNum = 0;
			if(playerVip!=null){
				playerVipNum = playerVip.getQuantity();
			}
			int randomNum = Integer.parseInt(indexsArr[0]);
//			log.debug("player:"+playerId+ " get the random index is " + randomNum +" in the "+ (time+1) + " time");
			log.debug("VipRandomStart/RandomIndex:\t"+playerId+ "\t" + randomNum +"\t"+ (time+1));
			if(playerVipNum<needVip){
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_VIP);
			}
			//玩家剩余的Vip数
			playerVipNum -=needVip;
			randomStartIndexs = "";
			for(int i=1;i<indexsArr.length;i++){
				randomStartIndexs+=indexsArr[i]+":";
			}
			//每一次开启把随机好的物品index数组的第一个返回给玩家，然后从数组中移除
			nosqlService.setRandomStartIndexs(playerId, randomStartIndexs);
			//获得玩家宝箱中15全部的item
			String[] vipRandoms = nosqlService.getVipRandomList(playerId).split(":");
			//获得玩家随机获得的奖励
			OnlineAward vsc = getService.getOnlineAwardByTypeAndId(Constants.ONLINE_AWARD_TYPES.VIP_SAFECABINET.getValue(),Integer.parseInt(vipRandoms[randomNum]));
//			OnlineAward vsc = getService.getOnlineAwardById(Integer.parseInt(vipRandoms[randomNum]));
			
//			log.debug("player:"+playerId+ " get the random safecabinet id is " + vipRandoms[randomNum] +" in the "+ (time+1) + " time");
			log.debug("VipRandomStart/Get:\t"+playerId+ "\t" + vipRandoms[randomNum] +"\t"+ (time+1));
			Payment pm = new Payment();
			pm.setUnit(vsc.getUnit());
			pm.setUnitType(vsc.getUnitType());
			SysItem si = getService.getSysItemByItemId(vsc.getItemId());
			//15、VIP保险柜子
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&& si.getId() == GrowthMissionConstants.UPGARDMOD_ID) {
				int upgradedNumByPId = getService.getUpgradedNumByPId(playerId);
				XunleiLogUtils.xlLog_22_1(player, 1, upgradedNumByPId, vsc.getUnit(),upgradedNumByPId + vsc.getUnit(), 15);
			}
			if(si!=null){
				createService.awardToPlayer(player, si, pm, null,Constants.BOOLEAN_YES);
//				log.info("Award to player:" + playerId + " sysItem:" +si.getId()+" when vip random start");
				log.info("VipRandomStart/Award:\t" + playerId + "\t" +si.getId() +"\t"+vsc.getUnitType()+"\t" + vsc.getUnit());
				}else{
//				log.error("Can not get SysItem by Id:"  +vsc.getItemId()+" when vip random start");
				log.error("VipRandomStart/SysItemNull:\t"  +vsc.getItemId());
				throw new BaseException(ExceptionMessage.NOT_GET_SYSITEM_BY_ID_MSG);
			}
			//稀有物品发公告
			int totalRare = si.getRareLevel();
			if(vsc.getUnitType() == Constants.NUM_ONE){
				totalRare *= vsc.getUnit();
			}
			int color = si.getIsStrengthen() == 1 ? si.getStrengthLevel() : 0;
			if(totalRare >= Constants.HIGH_RARE_LEVEL){
				soClient.proxyBroadCast(Constants.MSG_NBA, 
						Constants.SYSTEM_SPEAKER, CommonUtil.messageFormatI18N(CommonMsg.VIP_BOX_SYS, new Object[]{GunProperty.RED + "@!" + player.getName()+"@!"+GunProperty.RED + "@!" + player.getId(), color + "@!" + si.getDisplayName(),vsc.getUnit()}));
			}
			createService.updateItemQuantity(playerVip,needVip);
			deleteService.deletePlayerItemInMemcached(playerId, getService.getSysItemByItemId(playerVip.getItemId()));
			needVip = time>=Constants.VIP_OPEN_CHANCE_NUM-1?Constants.VIP_START_COSTS[Constants.VIP_OPEN_CHANCE_NUM-1]:Constants.VIP_START_COSTS[time+1];
			result = Converter.vipRandomStart(indexsArr.length-1,needVip,playerVipNum,randomNum);
			return result;
		} catch (BaseException e) {
			log.warn("VipRandomStart/Warn:\t",e);
			return Converter.warn(e.getMessage());
		}
		catch (Exception e) {
			log.error("VipRandomStart/Error:\t", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}

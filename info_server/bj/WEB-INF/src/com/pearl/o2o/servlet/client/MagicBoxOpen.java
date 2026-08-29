package com.pearl.o2o.servlet.client;

import java.util.List;

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

public class MagicBoxOpen extends BaseClientServlet {

	private static final long serialVersionUID = -847563471118712879L;
	private static Logger log = LoggerFactory.getLogger("magicbox");
	private static final String[] paramNames = { "pid","playeritemid" };
	
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int playerItemId = StringUtil.toInt(args[1]);
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			
			int totalHummerNum = 0;
			List<PlayerItem> playerHummers = getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_ITEM_TYPE, Constants.SPECIAL_ITEM_IIDS.PASSWORD_CARD.getValue());
			for(PlayerItem pi : playerHummers){
				totalHummerNum += pi.getQuantity();
			}
			PlayerItem playerHummer  = playerHummers.size()>0?playerHummers.get(0):null;
			PlayerItem playerMagicBox = getService.getPlayerItemByItemId(playerId,Constants.DEFAULT_OPEN_TYPE, playerItemId);
			int hummerNum = playerHummer!=null?playerHummer.getQuantity():0;
			int magicBoxNum = playerMagicBox==null?0:playerMagicBox.getQuantity();
			if(hummerNum<Constants.OPEN_MAGIC_BOX_NEED_NUM){
//				int maxQuantityId = CommonUtil.sortMaterial(playerId, Constants.SYSITEM_HUMMER_ID, playerHummers);
				int maxQuantityId = CommonUtil.sortMaterial(playerId, playerHummer.getItemId(), playerHummers);
				playerHummer = getService.getPlayerItemByItemId(playerId,Constants.DEFAULT_OPEN_TYPE, maxQuantityId);
				if(playerHummer.getQuantity()<Constants.OPEN_MAGIC_BOX_NEED_NUM){
					throw new BaseException(ExceptionMessage.NOT_ENOUGH_HUMMER);
				}
			}
			if(magicBoxNum<=0){
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_MAGIC_BOX);
			}
			
			List<OnlineAward> magicGifts = getService.getOnlineAwardByType(Constants.ONLINE_AWARD_TYPES.PASSWARD_BOX.getValue());
			OnlineAward award = getService.randomOnlineAwardFromList(Constants.NUM_ONE, magicGifts).get(0);
			Payment payment = new Payment();
			payment.setUnit(award.getUnit());
			payment.setUnitType(award.getUnitType());
			SysItem sysItem = getService.getSysItemByItemId(award.getItemId());
			award.setSysItem(sysItem);
			
			//14、密码盒子
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&& sysItem.getId() == GrowthMissionConstants.UPGARDMOD_ID) {
				int upgradedNumByPId = getService.getUpgradedNumByPId(playerId);
				XunleiLogUtils.xlLog_22_1(player, 1, upgradedNumByPId, award.getUnit(),upgradedNumByPId + award.getUnit(), 14);
			}
			
//			int totalHummerNum = getService.getPlayerItemsTotalQuantity(playerId,Constants.DEFAULT_MATERIAL_TYPE, Constants.SYSITEM_HUMMER_ID);
			
			createService.updateItemQuantity(playerHummer, Constants.OPEN_MAGIC_BOX_NEED_NUM);
			createService.updateItemQuantity(playerMagicBox);
			totalHummerNum-=Constants.OPEN_MAGIC_BOX_NEED_NUM;
			magicBoxNum--;
			if(sysItem!=null){
				createService.awardToPlayer(player, sysItem, payment, null, Constants.BOOLEAN_YES);
				log.info("MagicBoxOpen/Award:\t" + playerId + "\t" +sysItem.getId() + "\t" + award.getUnitType() + "\t" + award.getUnit());
				}else{
				log.warn("MagicBoxOpen/SysItemNull:\t"  +award.getItemId());
				throw new BaseException(ExceptionMessage.NOT_GET_SYSITEM_BY_ID_MSG);
			}
			int quantity = award.getUnit();
			if(award.getUnitType()!=Constants.DEFAULT_NUMBASE_TYPE)
				quantity=1;
			//稀有物品发公告
			int totalRare = sysItem.getRareLevel();
			if(award.getUnitType() == Constants.NUM_ONE){
				totalRare *= award.getUnit();
			}
			int color = sysItem.getIsStrengthen() == 1 ? sysItem.getStrengthLevel() : 0;
			if(totalRare >= Constants.HIGH_RARE_LEVEL){
				soClient.proxyBroadCast(Constants.MSG_NBA, Constants.SYSTEM_SPEAKER, 
						CommonUtil.messageFormatI18N(CommonMsg.MAGIC_BOX_SYS, new Object[]{GunProperty.RED + "@!" + player.getName()+"@!"+GunProperty.RED + "@!" + player.getId(), color + "@!" + sysItem.getDisplayName(),award.getUnit()}));
			}
			if(totalRare >= Constants.MAGIC_BOX_HIGH_RARE_LEVEL){
				nosqlService.addPlayerMagixBoxItemId(player, sysItem.getId(),quantity);
				
			}
			deleteService.deletePlayerItemInMemcached(playerId, getService.getSysItemByItemId(playerHummer.getItemId()));
			deleteService.deletePlayerItemInMemcached(playerId, getService.getSysItemByItemId(playerMagicBox.getItemId()));
			return Converter.magicBoxOpen(magicBoxNum,totalHummerNum,Constants.OPEN_MAGIC_BOX_NEED_NUM,award);

		} catch (BaseException e) {
			return Converter.warn(e.getMessage());
		}catch (Exception e) {
			log.error("MagicBoxOpen/Error:\t", e);
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
}

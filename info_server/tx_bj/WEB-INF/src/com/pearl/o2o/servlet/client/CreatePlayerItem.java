package com.pearl.o2o.servlet.client;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.CRNotEnoughException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.exception.STONENotEnoughException;
import com.pearl.o2o.pojo.BuyItemRecord;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.BiochemicalConstants;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.DateUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.FirstCheckUtils;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class CreatePlayerItem extends BaseClientServlet {
	private static final long serialVersionUID = 6356406975633293330L;
	static Logger log = LoggerFactory.getLogger(CreatePlayerItem.class.getName());
	private String[] paramNames = { "uid", "pid", "sid", "t", "cid", "costid", "packid" };

	@Override
	protected String innerService(String... args) {
		
		// int userId = StringUtil.toInt(args[0]);
	
		for(int i=1;i<args.length;i++){
		
			if(!args[i].matches("^\\d+$")){
				
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
		}
		int playerId = StringUtil.toInt(args[1]);
		int sysItemId = StringUtil.toInt(args[2]);
		int type = StringUtil.toInt(args[3]);
		int characterId = StringUtil.toInt(args[4]);
		int costId = StringUtil.toInt(args[5]);
		int packId = StringUtil.toInt(args[6]);

		
		StringBuffer sbBuffer=new StringBuffer(playerId+"|"+sysItemId+"|"+type+"|"+costId+"-");
		
		try {
			
			if (costId == 0) {
				throw new BaseException(ExceptionMessage.NOT_HAVE_PAYTYPE);
			}
			if (type > 3) {
				characterId = 0;
			}
			// 1.get player info by playerId
			Player player = getService.getPlayerById(playerId);
			

			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			
			// 2.get item info by sysitemId
			SysItem sysItem = getService.getSysItemByItemId(sysItemId);
			//消耗类必定是绑定状态; 
			
			if (type== Constants.DEFAULT_ZYZDZ_TYPE ||sysItem.getType()== Constants.DEFAULT_ZYZDZ_TYPE ) {// 资源争夺战物品
				if(sysItem.getSubType()==3){//坦克
					if (getService.playerItemUnderBuilding(playerId, sysItem)) {//该物品不是建造CD中
						throw new BaseException(ExceptionMessage.BUY_ITEM_CD);
					}
				}else if(sysItem.getSubType()==6){//个人技能，可以购买 check逻辑待定，可能会有 可能没有
					
				}
				else{//但是不是个人物品
					throw new BaseException(ExceptionMessage.BUY_FAIL);
				}
				
			}
			
			
			if((sysItem.getType()==Constants.DEFAULT_ITEM_TYPE&&sysItem.getSubType()==7)){
				packId=1;
			}
			
			int playerItemId = 0;
			
			//月卡叠加，逻辑特殊首次购买会赠送礼品
			if(sysItem.getIBuffId()==Constants.DEFAULT_ON_CARD_BUFF){
				//BUFF 叠加，如遇到当前角色购买月卡且该角色已经拥有，则购买实际上执行的是续费操作。2014/5/27 OuYangGuang
				List<PlayerItem> list = getService.getPlayerItemByItemIidAndIBuffId(playerId, 1, Constants.DEFAULT_ON_CARD_BUFF);
				PlayerItem onCard = list!=null && list.size()>0 ?list.get(0):null;
				
				//续费物品
				if(onCard!=null && Constants.BOOLEAN_NO.equals(sysItem.getIsDeleted())&& Constants.BOOLEAN_NO.equals(onCard.getIsDeleted())){	//月卡叠加
					updateService.renewPlayerItem(playerId, onCard.getId(), type, costId, false);
					playerItemId = onCard.getId();
				}
			}
			
			if(playerItemId==0){
				//zlm2015-8-4-start 8-26-改
				//游戏内市场物品购买 IS_DEFAULT N  IS_SHOW 1
				Payment payment =getService.getPaymentById(sysItem.getId(), costId);
				if (!CommonUtil.isZYZDZBuff(sysItem)// 不对资源争夺战的物品进行判断
						&& (	Constants.BOOLEAN_YES.equals(sysItem.getIsDeleted()) || (payment != null && payment
								.getIsShow() == 0))		)
						throw new NotBuyEquipmentException(ExceptionMessage.NOT_HAVE_PAYTYPE);
				//zlm2015-8-4-end	
				
				playerItemId = createService.buyPlayerItem(sbBuffer,player.getUserName(), player, sysItem, costId, Constants.BOOLEAN_NO, packId);
			
				if(playerItemId>0 && sysItem.getIBuffId()==Constants.DEFAULT_ON_CARD_BUFF){
					//第一次购买月卡会赠送福利
					if(FirstCheckUtils.getOnCardFirst(player)){	
						//黄金卡片
						SysItem sItem = getService.getSysItemByItemId(Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[0][1]);
						int count = 3; //赠送数量
						//getService.getSysItemByIID(Constants.SPECIAL_ITEM_IIDS.LUCKYPACKAGE_CARD.getValue(), Constants.DEFAULT_MATERIAL_TYPE);
						if(null != sItem)
						{
							String content = CommonUtil.messageFormatI18N(CommonMsg.ON_CARDS_FIRST_MESSAGE, count);
							//发送奖励
							ServiceLocator.createService.sendSystemMail(player, CommonUtil.messageFormatI18N("1"), content , sItem.getId(), new Payment(count,1));
						}
						
						ServiceLocator.createService.useItemById(player, sysItem.getType(), playerItemId, "", 0, 0);
					}
					
				}
			}
			
			if (playerItemId == -1) {
				throw new BaseException(ExceptionMessage.BUY_FAIL);
			}
			
			if (packId == 1 && playerItemId != 0) {
				if (type == Constants.DEFAULT_WEAPON_TYPE) {
					updateService.updateWeaponPackEquipment(playerId, type, playerItemId, characterId);
				} else if (type == Constants.DEFAULT_COSTUME_TYPE || type == Constants.DEFAULT_PART_TYPE) {
					updateService.updateCostumePackEquipment(playerId, type, playerItemId, characterId);
				}
				else if(sysItem.getType() == Constants.DEFAULT_ITEM_TYPE){
					if (sysItem.getSubType() < Constants.itemTypeArray[0][4]) {
						ServiceLocator.createService.useItemById(player, sysItem.getType(), playerItemId, "", 0, 0);
					}
					if ((sysItem.getIBuffId() == 7 || sysItem.getIBuffId() == 8|| sysItem.getIBuffId() == BiochemicalConstants.ordinaryBuffId|| sysItem.getIBuffId() == BiochemicalConstants.especialBuffId)) {
						
						ServiceLocator.createService.useItemById(player, sysItem.getType(), playerItemId, "", 0, 0);
					}
					if(sysItem.getIId() == 15){
						ServiceLocator.createService.useItemById(player, sysItem.getType(), playerItemId, "", 0, 0);
					}
				}		
			}
			
			
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sPlayer(playerId));
			// mcc.delete(CacheUtil.oStorage(playerId,type,0));

			deleteService.deletePlayerItemInMemcached(playerId, sysItem);

			return Converter.createPlayerItem(playerItemId, null,sysItem.getTimeForCreateMsec());
		}catch (STONENotEnoughException nbe) {
			log.debug("STONENotEnoughException in CreatePlayerItem"+ nbe.getMessage());
			return Converter.createPlayerItem(-7, nbe.getMessage());
		}catch (NotBuyEquipmentException e) {
			log.debug("NotBuyEquipmentException in CreatePlayerItem"+ e.getMessage());
			return Converter.createPlayerItem(0,e.getMessage());
		}
		catch (CRNotEnoughException nbe) {
			log.debug("CRNotEnoughException in CreatePlayerItem"+ nbe.getMessage());
//			return Converter.createPlayerItem(-2, nbe.getMessage());
			return Converter.createPlayerItem(-2, "");
		}catch (Exception e) {
			log.warn("Exception in CreatePlayerItem", e);
			if(e instanceof BaseException){
				return e.getMessage();
			}
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}finally{
			if(!sbBuffer.toString().endsWith("-")){
				infoLogger.log(LogServerMessage.buyPlayerItem.name(), Level.INFO_INT, 
						LogUtils.JoinerByTab.join("0.1",CommonUtil.simpleDateFormat.format(new Date()),sbBuffer.toString()));
			}

		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}

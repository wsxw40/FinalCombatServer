package com.pearl.o2o.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.exception.ReceiverNotExistException;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.TeamConstants;

public class MessageService  extends BaseService{
	private static Logger logger = LoggerFactory.getLogger(MessageService.class);
	private static final Player SYSTEM_SENDER = new Player(); 
	static {
		SYSTEM_SENDER.setId(0);
		SYSTEM_SENDER.setName(Constants.SYSTEM_NAME);
	}
	
	private CreateService createService;
	
	private void updateNewMailNums(Player player) throws Exception{
		int num=getService.getNewMessageNum( player.getId());
		soClient.messageCMD(player.getName(),CommonUtil.messageFormat(CommonMsg.REFRESH_EMAIL_NUM, num));
	}
	
//	public void updateTeamApplicationNums(Player player) throws Exception{
//		int num=getService.getNewMessageNum(player.getUserId(), player.getId());
//		soClient.messageCMD(player.getName(),CommonUtil.messageFormat(CommonMsg.REFRESH_EMAIL_NUM, num));
//	}
	

	public void createMessageCommon(Message message,Player receiver)throws Exception{
		message=messageDao.createMessage(message);
		if (1==message.getIsAttached()) {
			messageDao.createMessageItem(message);
		}
		message.init();
		//if is a mail with attachment, do not add it into mail box
//		if(message.getIsAttached()!=1){
		nosqlService.addOutMessage(message);
//		}
		try{
			mcc.deleteWithNoReply(CacheUtil.oPlayerMessage(message.getReceiverId()));
			mcc.deleteWithNoReply(CacheUtil.sPlayerMessage(message.getReceiverId()));
			updateNewMailNums(receiver);
		}catch(Exception e){//if push news fail, don't roll-back
			logger.warn("error happend during push cmd for new message, messageId:" + message.getId() + "exception is " + e);
		}
	}

	public void createMessage(Player sender,Player receiver ,String subject, String content,int itemId)throws Exception{
		if(itemId!=0){
			PlayerItem pi=getService.getPlayerItemById(sender.getId(), itemId);
			if(Constants.BOOLEAN_YES.equals(pi.getIsBind())){
				throw new BaseException(ExceptionMessage.NOT_GIFT_BIND);
			}
			int sysItemId = pi.getSysItem().getId();
			createMessage(sender,receiver,subject,content,0,0,sysItemId+",",itemId);
		}else{
			createMessage(sender,receiver,subject,content,0,0,"",0);
		}
	}
	
	
	/**
	 * method to create a email with a item attachment 
	 * @param userId
	 * @param playerId
	 * @param receiverId
	 * @param itemId
	 * @param costType
	 * @return
	 * @throws Exception
	 */

	public void createMessage(Player sender,Player receiver ,String subject, String content ,int itemId,int costId,String itemIds,int giftId)throws Exception{
		if (receiver == null) {
			throw new ReceiverNotExistException();
			//return ;
		}
		Message message=new Message();
		message.setSender(sender.getName());
		message.setSenderId(sender.getId());
		message.setReceiverId(receiver.getId());
		message.setReceiver(receiver.getName());;
		message.setSubject(subject);
		message.setContent(content);
		message.setItems(itemIds);
		if (itemId !=0 && costId !=0){
			SysItem sysItem=sysItemDao.getSystemItemById(itemId);
			//zlm2015-8-4-start 
			//玩家之间的游戏内市场物品购买直接赠送 IS_DEFAULT N  IS_SHOW 1
			Payment payment =getService.getPaymentById(sysItem.getId(), costId);
			if (Constants.BOOLEAN_YES.equals(sysItem.getIsDeleted()) 
					|| (	payment != null && payment.getIsShow() == 0)	) //payment.getIsShow()==0 该支付价格不能使用
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_HAVE_PAYTYPE);
			//zlm2015-8-4-end	
//			sysItem.init();
			mcc.delete(CacheUtil.oPlayer(sender.getId()));
			mcc.delete(CacheUtil.sPlayer(sender.getId()));
			mcc.delete(CacheUtil.oPlayerMessage(receiver.getId()));
			mcc.delete(CacheUtil.sPlayerMessage(receiver.getId()));
			int playerItemId = 0;
			//if sender is system sender, just call send item
			if (sender.getId() != SYSTEM_SENDER.getId()) {
				 playerItemId = createService.presentItem(receiver, sender, sysItem, costId,Constants.BOOLEAN_YES);
			}else {
//				 playerItemId = createService.sendItems(sysItem,receiver,Constants.BOOLEAN_YES, receiver.getId(),1 ,0);
			}
			
			message.setPlayerItemId(playerItemId);
			message.setIsAttached(1);
		}
		if(giftId!=0){
			PlayerItem pi=getService.getPlayerItemById(sender.getId(), giftId);
			pi.setIsGift(Constants.BOOLEAN_YES);
			if(pi.getSysItem().getType()==Constants.DEFAULT_ITEM_TYPE&&pi.getSysItem().getIId()==27){//friend package
				pi.setIsBind(Constants.BOOLEAN_YES);
			}
			ServiceLocator.updateService.updatePlayerItem(pi);
			
			message.setPlayerItemId(giftId);
			message.setIsAttached(1);
		}
		createMessageCommon(message,receiver);
	}
	/**
	 * 通过指定payment发送附件
	 * @param sender
	 * @param receiver
	 * @param subject
	 * @param content
	 * @param itemId
	 * @param payment
	 * @param isAttached
	 * @throws Exception
	 */
	public void createMessage(Player sender,Player receiver ,String subject, String content ,int itemId,Payment payment ,int isAttached)throws Exception{
		if (receiver == null) {
			throw new ReceiverNotExistException();
			//return ;
		}
		Message message=new Message();
		message.setSender(sender.getName());
		message.setSenderId(sender.getId());
		message.setReceiverId(receiver.getId());
		message.setReceiver(receiver.getName());;
		message.setSubject(subject);
		message.setContent(content);
//		message.setItems(itemIds);
		if (itemId !=0 && isAttached !=0){
			SysItem sysItem=sysItemDao.getSystemItemById(itemId);
//			sysItem.init();
			mcc.delete(CacheUtil.oPlayer(sender.getId()));
			mcc.delete(CacheUtil.sPlayer(sender.getId()));
			mcc.delete(CacheUtil.oPlayerMessage(receiver.getId()));
			mcc.delete(CacheUtil.sPlayerMessage(receiver.getId()));
			int playerItemId = 0;
			//if sender is system sender, just call send item
			if (sender.getId() != SYSTEM_SENDER.getId()) {
				 playerItemId = createService.buyPlayerItem(null,sender.getUserName(), sender, sysItem, payment,Constants.BOOLEAN_YES,0,receiver);
			}else {
				 playerItemId = createService.sendItem(sysItem,receiver,payment,Constants.BOOLEAN_YES,Constants.BOOLEAN_YES,Constants.BOOLEAN_NO);
			}
			
			message.setPlayerItemId(playerItemId);
			message.setIsAttached(1);
		}
		createMessageCommon(message,receiver);
	}
	public void createMessage(Player sender,Player receiver ,String subject, String content ,int itemId,int costId,String itemIds,String itemsUnits ,String itemsUnitTypes,int giftId)throws Exception{
		if (receiver == null) {
			throw new ReceiverNotExistException();
			//return ;
		}
		Message message=new Message();
		message.setSender(sender.getName());
		message.setSenderId(sender.getId());
		message.setReceiverId(receiver.getId());
		message.setReceiver(receiver.getName());;
		message.setSubject(subject);
		message.setContent(content);
		message.setItemsUnits(itemsUnits);
		message.setItemsUnitTypes(itemsUnitTypes);
		message.setItems(itemIds);
		
		if (itemId !=0 && costId !=0){
			SysItem sysItem=sysItemDao.getSystemItemById(itemId);
//			sysItem.init();
			mcc.delete(CacheUtil.oPlayer(sender.getId()));
			mcc.delete(CacheUtil.sPlayer(sender.getId()));
			mcc.delete(CacheUtil.oPlayerMessage(receiver.getId()));
			mcc.delete(CacheUtil.sPlayerMessage(receiver.getId()));
			int playerItemId = 0;
			//if sender is system sender, just call send item
			if (sender.getId() != SYSTEM_SENDER.getId()) {
				playerItemId = createService.presentItem(receiver, sender, sysItem, costId,Constants.BOOLEAN_YES);
			}else {
//				 playerItemId = createService.sendItems(sysItem,receiver,Constants.BOOLEAN_YES, receiver.getId(),1 ,0);
			}
			
			message.setPlayerItemId(playerItemId);
			message.setIsAttached(1);
		}
		if(giftId!=0){
			PlayerItem pi=getService.getPlayerItemById(sender.getId(), giftId);
			pi.setIsGift(Constants.BOOLEAN_YES);
			if(pi.getSysItem().getType()==Constants.DEFAULT_ITEM_TYPE&&pi.getSysItem().getIId()==27){//friend package
				pi.setIsBind(Constants.BOOLEAN_YES);
			}
			ServiceLocator.updateService.updatePlayerItem(pi);
			
			message.setPlayerItemId(giftId);
			message.setIsAttached(1);
		}
		createMessageCommon(message,receiver);
	}
	
	/**
	 * send a gift mail to player who plays 10 round
	 * @throws Exception 
	 */
	public void sendSystemGiftMailForNewPlayer(Player player) throws Exception{
		//TODO
		//id=64 -> 轻型防弹衣
		sendSystemMail(player,CommonMsg.A_GIFT,CommonUtil.messageFormatI18N(CommonMsg.SYSGIFT_NEWPLAYER_MAIL,player.getName()),64,1);
	}
	
	public void sendSystemMail(Player receiver ,String subject, String content ,int itemId,int costType ) throws Exception{
		createMessage(SYSTEM_SENDER,receiver,subject,  content , itemId, costType ,"",0);
	}
	
	public void sendSystemMailWithSysItemInContent(Player receiver ,String subject, String content ,String itemsId) throws Exception{
		createMessage(SYSTEM_SENDER,receiver,subject,  content , 0, 0 ,itemsId,0);
	}
	public void sendSystemMailWithSysItemInContent(Player receiver ,String subject, String content ,String itemsId,String itemUnits,String itemUnitTypes) throws Exception{
		createMessage(SYSTEM_SENDER,receiver,subject,  content , 0, 0 ,itemsId,itemUnits,itemUnitTypes,0);
	}
	public void sendSystemMailWithSysItemInContent( Player receiver ,String subject, String content ,int itemId,Payment payment ) throws Exception{
		createMessage(SYSTEM_SENDER, receiver , subject,  content , itemId, payment , 1);
	}
	
	public void sendSystemMail(Player receiver ,String subject, String content) throws Exception{
		sendSystemMail(receiver,subject,content,0,0);
	}
	
	public void sendWelcomeMail(Player p) throws Exception{
		sendSystemMail(p, CommonMsg.WELCOME, ConfigurationUtil.WELCOME_EMAIL);
	}
	
	public void sendExpiredItemNotifyMail(Player player, List<PlayerItem> list, String ids) throws Exception{
		sendSystemMailWithSysItemInContent(player, CommonMsg.OUT_OF_DATE_NOTICE, Converter.expiredItemNotify(list), ids);
	}
	public void sendExpiredPackMail(Player player, List<PlayerItem> list, String ids) throws Exception{
		sendSystemMailWithSysItemInContent(player, CommonMsg.PACK_EXPIRE_NOTICE, Converter.expiredPackNotify(list),ids);
	}
	public void sendDurableNotifyMail(Player player, List<PlayerItem> list, String ids) throws Exception{
		sendSystemMailWithSysItemInContent(player, CommonMsg.NAI_JIU_NOTICE, Converter.durableNotify(list),ids);
	}
	
	public void sendTeamDismissNotifyMail(Player player, Team team) throws Exception{
		sendSystemMail(player, CommonMsg.TEAM_DISMISS, CommonUtil.messageFormatI18N(CommonMsg.TEAM_DISMISS_MAIL, team.getName()));
	}
	
	public void sendFiredFromTeamNotifyMail(Player player, Team team) throws Exception{
		sendSystemMail(player, CommonMsg.TEAM_FIRED, CommonUtil.messageFormatI18N(CommonMsg.TEAM_FIRED_MAIL, team.getName()) );
	}
	
	public void sendQuitFromTeamNotifyMail(Player player, Player quit) throws Exception{
		sendSystemMail(player, CommonMsg.TEAM_QUIT, CommonUtil.messageFormatI18N(CommonMsg.TEAM_QUIT_MAIL, quit.getName()));
	}
	
	public void sendRejectedJoinTeamNotifyMail(Player player, Team team) throws Exception{
		sendSystemMail(player, CommonMsg.TEAM_REJECT, CommonUtil.messageFormatI18N(CommonMsg.TEAM_REJECT_MAIL,team.getName()));
	}
	
	public void sendApprovedNotifyMail(Player player,Team team) throws Exception{
		sendSystemMail(player, CommonMsg.TEAM_APPLY, CommonUtil.messageFormatI18N(CommonMsg.TEAM_APPLY_CONTENT,team.getName()));
	}
	
	public void sendAppointNotifyMail(Player player,Player opPlayer,Team team,PlayerTeam playerteam) throws Exception{
		sendSystemMail(player, CommonMsg.TEAM_APPOINT, CommonUtil.messageFormatI18N(CommonMsg.TEAM_APPOINT_CONTENT,opPlayer.getName(),team.getName(),TeamConstants.TEAMJOB.getTypeByValue(playerteam.getJob()).getTitle()));
	}
	
	public CreateService getCreateService() {
		return createService;
	}

	public void setCreateService(CreateService createService) {
		this.createService = createService;
	}
}

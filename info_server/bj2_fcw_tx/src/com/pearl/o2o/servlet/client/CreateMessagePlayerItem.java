package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class CreateMessagePlayerItem extends BaseClientServlet {
	private static final long serialVersionUID = -1077440198506335030L;
	static Logger LOG=LoggerFactory.getLogger(CreateMessagePlayerItem.class.getName());
	private String[] paramNames={"pid","rid","itemId",};
	@Override
	protected String innerService(String... args) {
		try{
			int playerId= StringUtil.toInt(args[0]);
			int receiverId= StringUtil.toInt(args[1]);
			int itemId= StringUtil.toInt(args[2]);
			Player sender=getService.getSimplePlayerById(playerId);
			if(sender==null){
				throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
			}
			Player receiver=getService.getSimplePlayerById(receiverId);
			if(receiver==null){
				throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
			}
			PlayerItem pi=getService.getPlayerItemById(playerId, itemId);
			if(pi==null){
				throw new BaseException(ExceptionMessage.NOT_PLAYER_ITEM);
			}
			if(Constants.BOOLEAN_YES.equals(pi.getIsBind())){
				throw new BaseException(ExceptionMessage.NOT_GIFT_BIND);
			}
			String giftSubjectAndContent = CommonUtil.messageFormatI18N(CommonMsg.GIFT_EMAIL,sender.getName());
			if(receiver != null){
				pi.setIsGift("Y");
				updateService.getPlayerItemDao().updatePlayerItem(pi);
				deleteService.deletePlayerItemInMemcached(playerId, pi.getSysItem());
				Message message=new Message();
				message.setSender(sender.getName());
				message.setSenderId(sender.getId());
				message.setReceiverId(receiver.getId());
				message.setReceiver(receiver.getName());
				message.setSubject(giftSubjectAndContent);
				message.setContent(giftSubjectAndContent);
				message.setItems(""+pi.getItemId());
				message.setPlayerItemId(pi.getId());
				message.setIsAttached(1);
				messageService.createMessageCommon(message, receiver);
			}
			
			return Converter.createMessage(Constants.NUM_ZERO, null, 0, 0,1);
		}
		catch (BaseException be) {
			LOG.debug("Exception in CreateMessageMode",be);
			return Converter.createMessage(Constants.NUM_ONE,be.getMessage(),0,0,1);	
		}
		catch (Exception e) {
			LOG.warn("Exception in CreateMessageMode",e);
			return Converter.createMessage(Constants.NUM_ONE,e.getMessage(),0,0,1);		
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

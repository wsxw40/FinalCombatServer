package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.CRNotEnoughException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class CreateMessageItem extends BaseClientServlet {
	private static final long serialVersionUID = -1077440198506335030L;
	static Logger LOG=LoggerFactory.getLogger(CreateMessageItem.class.getName());
	private String[] paramNames={"uid","pid","rid","sid","t","costid"};
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId= StringUtil.toInt(args[1]);
			int receiverId= StringUtil.toInt(args[2]);
			int itemId= StringUtil.toInt(args[3]);
			int type=StringUtil.toInt(args[4]);
			int costId=StringUtil.toInt(args[5]);
			if(costId==0){
				 throw new BaseException(ExceptionMessage.NOT_HAVE_PAYTYPE);
			}
			SysItem si=getService.getSysItemByItemId(itemId);
			if(si==null||si.getIsHot()!=0){
				LOG.warn("not gift ishot="+si.getIsHot());
				throw new BaseException(ExceptionMessage.NOT_GIFT);
			}
			
			//检测是否输过二级密码
			if(!checkEnterSPW(Integer.valueOf(playerId))){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			Player sender = getService.getSimplePlayerById(playerId);
			Player receiver = getService.getSimplePlayerById(receiverId);
			String giftSubjectAndContent = CommonUtil.messageFormatI18N(CommonMsg.GIFT_EMAIL,sender.getName(),receiver.getName());
			if(receiver != null){
				messageService.createMessage(sender,receiver, giftSubjectAndContent,giftSubjectAndContent, itemId, costId,"",0);
			}
			if(costId==0){
				return Converter.createMessage(Constants.NUM_ONE,"cost id == 0",0,0,1);
			}
			return Converter.createMessage(Constants.NUM_ZERO, null, 0, 0,1);
		}
		catch (CRNotEnoughException e) {
			LOG.debug("CRNotEnoughException in CreateMessageMode",e);
			return Converter.createMessage(Constants.NUM_ONE,null,0,0,-2);	
		}
		catch (BaseException be) {
			LOG.debug("Exception in CreateMessageMode",be);
			return Converter.createMessage(Constants.NUM_ONE,be.getMessage(),0,0,1);	
		}catch (NotBuyEquipmentException be) {
			LOG.debug("Exception in CreateMessageMode:"+be.getMessage());
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
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}

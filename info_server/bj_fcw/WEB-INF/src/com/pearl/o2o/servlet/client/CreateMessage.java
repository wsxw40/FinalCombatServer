package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.StringUtil;

public class CreateMessage extends BaseClientServlet {

	private static final long serialVersionUID = -4665043181234763917L;
	static Logger log = LoggerFactory.getLogger(CreateMessage.class.getName());
	private String[] paramNames={"uid","cid","receiver","subject","content","itemId",};
	@Override
	protected String innerService(String... args) {
		try{
//			int userId = StringUtil.toInt(args[0]);
			int playerId= StringUtil.toInt(args[1]);
			String receiverName= args[2];
			String subject= args[3];
			String content= args[4];
			int itemId=  StringUtil.toInt(args[5]);
			subject=KeywordFilterUtil.filter(StringUtil.escapeIndex(subject));
			content=KeywordFilterUtil.filter(StringUtil.escapeIndex(content));
			if(StringUtil.isEmptyString(subject)){
				throw new BaseException(ExceptionMessage.EMPTY_STR);
			}else if(subject.length()>16){
				throw new BaseException(ExceptionMessage.TOO_LONG);
			}
			if(StringUtil.isEmptyString(content)){
				throw new BaseException(ExceptionMessage.EMPTY_STR);
			}else if(content.length()>140){
				throw new BaseException(ExceptionMessage.TOO_LONG);
			}
			//检测是否输过二级密码
			if(!checkEnterSPW(Integer.valueOf(playerId))){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			
			
			subject=StringUtil.stringToAscii(subject);
			content=StringUtil.stringToAscii(content);
			Player sender = getService.getSimplePlayerById(playerId);
			Player receiver = getService.getPlayerByName(receiverName);
			CommonUtil.checkNull(receiver,ExceptionMessage.NOT_FIND_PLAYER);
			
			//当前玩家被拉黑，禁止发送邮件给对方 20140626 OuYangGuang
			
			if(getService.getFriendDao().getFriendIndex(receiver.getId(),sender.getId(),Constants.BLACKLIST)!=null)
			{
				throw new BaseException(ExceptionMessage.FRIEND_BLACK);
			}
			
			messageService.createMessage(sender,receiver, subject, content,itemId);
			
			//成长任务：通过邮件赠送好友物品
			if(itemId != 0 && getService.getPlayerItemById(playerId, itemId).getSysItem().getIId()==27){
				Friend fri=getService.getFriendIndexById(playerId,receiver.getId(), Constants.FRIEND);
				if (null != fri && Constants.BOOLEAN_YES.equals(fri.getAcceptted())) {
					boolean b = updateService.updatePlayerGrowthMission(sender, GrowthMissionConstants.GrowthMissionType.PRESENT_BY_EMAIL);
					System.out.println("Task Result : "+b);
					
				}
			}
			
			return Converter.createMessage(Constants.NUM_ZERO,null);
		}
		catch (BaseException be) {
			log.debug("Exception in CreateMessage"+be.getMessage());
			return Converter.createMessage(Constants.NUM_ONE,be.getMessage());	
		}
		catch (Exception e) {
			log.warn("Exception in CreateMessage",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
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

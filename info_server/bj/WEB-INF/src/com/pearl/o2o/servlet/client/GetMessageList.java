package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetMessageList extends BaseClientServlet {

	private static final long serialVersionUID = 585111220320711936L;
	static Logger log = LoggerFactory.getLogger(GetMessageList.class.getName());
	private String[] paramNames={"uid","cid","type"};
	@Override
	protected String innerService(String... args) {
		String result;
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int boxtype = Integer.valueOf(args[2]);//0 in, 1 out
			List<Message> messageList=new ArrayList<Message>();
			
			if (boxtype == 0){
				//String strKey=CacheUtil.sPlayerMessage(playerId);
				//result= mcc.get(strKey,Constants.CACHE_TIMEOUT);
				//if(result==null){
					messageList=getService.getMessageListFromInbox(userId,playerId);
					result=Converter.messageList(messageList, boxtype);
					//mcc.set(strKey, Constants.CACHE_ITEM_TIMEOUT, result,Constants.CACHE_TIMEOUT);
				//}
				return result;
			}else if (boxtype == 1){
				 Player p = getService.getSimplePlayerById(playerId);
				 messageList=nosqlService.getOutMessageList(playerId, p.getName());
				 result=Converter.messageList(messageList, boxtype);
				 return result;
			}else {
				throw new Exception("unknown box type :" + boxtype);
			}
		}catch(Exception e){
			log.warn("Error in GetMessageList: " ,e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}

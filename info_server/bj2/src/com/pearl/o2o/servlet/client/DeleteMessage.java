package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;


public class DeleteMessage extends BaseClientServlet {
	private static final long serialVersionUID = 7986649272863124341L;
	static Logger log = LoggerFactory.getLogger(GetMessageList.class.getName());
	private String[] paramNames={"uid","cid","mid","type"};	
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			String messages=args[2];
			int type = Integer.valueOf(args[3]);//0 in, 1 out
//			long start=System.currentTimeMillis();
			String message[]=messages.split(",");
			if(type == 0){
				for(int i=0;i<message.length;i++){
					if(message[i]!=null&&!"".equals(message[i])){
						int messageId = StringUtil.toInt(message[i]);
						Message messageFromInbox = getService.getMessageFromInbox(playerId, messageId);
						if (null != messageFromInbox.getPlayerItem()) {
							log.warn("player playerId="+playerId+" delete deleteMessage type="+type+" messageId"+messageId+" playerItemId="+messageFromInbox.getPlayerItem().getId());
						}
						updateService.updateMessage(playerId,messageId);
					}
				}
//				System.out.println("删除收件箱邮件结束："+(System.currentTimeMillis()-start));
			}else if (type ==1){
				Player p = getService.getSimplePlayerById(playerId);
				List<String> messageIds = new ArrayList<String>();
				messageIds.addAll(Arrays.asList(message));
				nosqlService.deleteOutMessages(playerId, p.getName(), messageIds);
//				System.out.println("删除发件箱邮件结束："+(System.currentTimeMillis()-start));
			}
			return Converter.createMessage(Constants.NUM_ZERO, null);
		}
		catch (BaseException be) {
			log.debug("Exception in deleteMessage:"+be.getMessage());
			return Converter.createMessage(Constants.NUM_ONE,be.getMessage());	
		}
		catch(Exception e){
			log.warn("Error in DeleteMessage: " , e);
			return Converter.createMessage(Constants.NUM_ONE,e.getMessage());	
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}

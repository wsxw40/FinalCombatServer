package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetMessage extends BaseClientServlet {
	private static final long serialVersionUID = 585111220320711936L;
	static Logger log = LoggerFactory.getLogger(GetMessage.class.getName());
	private String[] paramNames={"uid","cid","mid","type"};

	@Override
	protected String innerService(String... args) {
		try {
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			int messageId = StringUtil.toInt(args[2]);
			int type = StringUtil.toInt(args[3]);
			Message message = null;
			if (type == 0){//0 in, 1 out
				message = getService.getMessageFromInbox(playerId, messageId);
			}else {
				message = getService.getMessageFromOutBox(userId, playerId, messageId);
			}
			if(null == message || message.getId() == 0){
				return "";
			}
			SysItem si=null;
			if(Constants.BOOLEAN_YES.equals(message.getHaveAttached())){
				if(type==0){
					si=getService.getSysItemByItemId(message.getPlayerItem().getItemId());
					si.initGunProperty();
				}else if(type==1){
					PlayerItem pi = getService.getPlayerItemById(playerId, message.getPlayerItemId());
					if(pi!=null){
						message.setHaveAttached("N");
						message.setItemsUnits(String.valueOf(pi.getQuantity()));
						message.setItemsUnitTypes(String.valueOf(pi.getPlayerItemUnitType()));
						message.setItems(String.valueOf(pi.getItemId()));
					}
				}

			}
            //FCW 统一编码输出
            message.setSender(StringUtil.stringToAscii(message.getSender()));
            message.setSubject(StringUtil.stringToAscii(message.getSubject()));
            message.setContent(StringUtil.stringToAscii(message.getContent()));
            message.setCreatedDateStr(StringUtil.stringToAscii(message.getCreatedDateStr()));
            message.setCreatedTimeStr(StringUtil.stringToAscii(message.getCreatedTimeStr()));
            //FCW
            return Converter.message(message, si);
		} catch (Exception e) {
			log.warn("Error in GetMessage: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}

package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.DBRouteUtil;

public class MessageDao extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public Message createMessage(Message message) throws DataAccessException {
		int id = (Integer) this.getSqlMapClientTemplate().insert("Message.insert", message);
		message.setId(id);
		message.setCreatedTime(new Date());//TODO : may be a bug, if the app time is not same with db time
		return message;
	}

	@SuppressWarnings("unchecked")
	public void createMessageItem(Message message) throws DataAccessException {
		int mId = message.getId();
		int messageModeId = (Integer)this.getSqlMapClientTemplate().insert("Message.insertMessageItem", message);
		message.setId(mId);
		message.setMessageModeId(messageModeId);
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessageListFromInBox(Integer userId, int playerId)throws DataAccessException{
		Map map=new HashMap();
		map.put("receiverId", playerId);
//		logger.error("DBRouteUtil.getTableSuffix(PlayerItem.class, playerId)="+DBRouteUtil.getTableSuffix(PlayerItem.class, playerId));
//		map.put("tableSuffix", DBRouteUtil.getTableSuffix(PlayerItem.class, playerId));
		return this.getSqlMapClientTemplate().queryForList("Message.selectFromInBox", map);
	}


	
	@SuppressWarnings("unchecked")
	public Message getMessageFromOutBoxById(Integer userId, int playerId,int messageId)throws DataAccessException{
		Map map=new HashMap();
		map.put("messageId", messageId);
		return (Message)this.getSqlMapClientTemplate().queryForObject("Message.selectFromOutBox", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessageFromOutBoxBySenderName(String senderName,int id)throws DataAccessException{
		Map map=new HashMap();
		map.put("senderName", senderName);
		if(id!=0){
			map.put("receiverId", id);
		}
		return (List<Message>)this.getSqlMapClientTemplate().queryForList("Message.selectFromOutBox", map);
	}

	@SuppressWarnings("unchecked")
	public void deleteMessage(int messageId)throws DataAccessException{
		this.getSqlMapClientTemplate().update("Message.deleteInbox",messageId);
	}

	public void updateMessageOpen(int messageId)throws DataAccessException{
		this.getSqlMapClientTemplate().update("Message.updateMessageOpen",messageId);
	}

	@SuppressWarnings("unchecked")
	public void updateMessageContent(String message,String itemsId ,String unit,String unitType,int messageId)throws DataAccessException{
		Map map=new HashMap();
		map.put("message", message);
		map.put("itemsId", itemsId);
		map.put("unit", unit);
		map.put("unitType", unitType);
		map.put("messageId", messageId);
		this.getSqlMapClientTemplate().update("Message.updateMessageContent",map);
	}

	@SuppressWarnings("unchecked")
	public void deleteMessageItem(int messageItemId)throws DataAccessException{
		this.getSqlMapClientTemplate().delete("Message.deleteMessageItem",messageItemId);
	}
}

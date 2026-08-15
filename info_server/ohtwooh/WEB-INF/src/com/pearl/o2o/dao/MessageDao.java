package com.pearl.o2o.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.Message;

public class MessageDao extends SqlMapClientDaoSupport {
	
	public Message createMessage(Message message) throws DataAccessException {
		int id = (Integer) this.getSqlMapClientTemplate().insert("Message.insert", message);
		message.setId(id);
		return message;
	}

	public void createMessageItem(Message message) throws DataAccessException {
		int messageModeId = (Integer)this.getSqlMapClientTemplate().insert("Message.insertMessageItem", message);
		message.setMessageModeId(messageModeId);
	}
	public List<Message> getMessageList(Integer userId, int playerId)throws DataAccessException{
		Map map=new HashMap();
		map.put("receiverId", playerId);
		return this.getSqlMapClientTemplate().queryForList("Message.select", map);
	}
	public Message getMessageById(Integer userId, int playerId,int messageId)throws DataAccessException{
		Map map=new HashMap();
		map.put("receiverId", playerId);
		map.put("messageId", messageId);
		return (Message)this.getSqlMapClientTemplate().queryForObject("Message.select", map);
	}
	public void deleteMessage(int messageId)throws DataAccessException{
		this.getSqlMapClientTemplate().update("Message.delete",messageId);
	}
	public void updateMessageOpen(int messageId)throws DataAccessException{
		this.getSqlMapClientTemplate().update("Message.updateMessageOpen",messageId);
	}
	public void deleteMessageItem(int messageItemId)throws DataAccessException{
		this.getSqlMapClientTemplate().delete("Message.deleteMessageItem",messageItemId);
	}
	
}

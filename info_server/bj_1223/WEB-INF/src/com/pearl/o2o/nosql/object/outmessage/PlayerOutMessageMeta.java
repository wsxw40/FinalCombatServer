package com.pearl.o2o.nosql.object.outmessage;

import com.pearl.o2o.nosql.object.PlayerNosqlRecord;
import com.pearl.o2o.pojo.Message;

public class PlayerOutMessageMeta extends PlayerNosqlRecord {
	public static final NoSqlDataType DATATYPE = NoSqlDataType.LIST;
	public static final String 	RECORDTYPE = "PlayerOutMessageMeta";
	
	private String senderName;
	private String receiverName;
	private String subject;
	private String date;
	private int isAttached;
	private int messageId;
	private String contentKey;
	
	
	public PlayerOutMessageMeta(int playerId, String playerName, Message message, String contentKey) {
		super(playerId, playerName);
		this.senderName = message.getSender();
		this.receiverName = message.getReceiver();
		this.subject = message.getSubject();
		this.date = message.getCreatedDateStr();
		this.isAttached = message.getIsAttached();
		this.messageId = message.getId();
		this.contentKey = contentKey;
	}

	public PlayerOutMessageMeta(int playerId, String playerName,String nosqlStr) {
		super(playerId, playerName);
		String[] fields = nosqlStr.split("\\" + DELEMETER);
		
		
		this.senderName = fields[0];
		this.receiverName = fields[1];
		this.subject = fields[2];
		this.date = fields[3];
		this.isAttached = Integer.valueOf(fields[4]);
		this.contentKey = fields[5];
		this.messageId = Integer.valueOf(fields[6]);
	}
	
	public Message toMessage(){
		Message result = new Message();
		result.setId(messageId);
		result.setCreatedDateStr(date);
		result.setCreatedTimeStr(date);
		result.setReceiver(receiverName);
		result.setSender(senderName);
		result.setSubject(subject);
		
		return result;
	}
	
	
	@Override
	protected NoSqlDataType getDataTypeKey() {
		return DATATYPE;
	}

	@Override
	protected String getRecordType() {
		return RECORDTYPE;
	}
	
	@Override
	//senderName|receiverName|subject|date|isAttached|contentKey|messageId
	public String generateNosqlPlainStr() {
		return new StringBuilder().append(senderName).append(DELEMETER).
					append(receiverName).append(DELEMETER)
					.append(subject).append(DELEMETER)
					.append(date).append(DELEMETER)
					.append(isAttached).append(DELEMETER)
					.append(contentKey).append(DELEMETER)
					.append(messageId)
					.toString();
	}
	


	public static NoSqlDataType getDatatype() {
		return DATATYPE;

	}

	public static String getRecordtype() {
		return RECORDTYPE;
	}



	public String getSenderName() {
		return senderName;
	}



	public String getReceiverName() {
		return receiverName;
	}



	public String getSubject() {
		return subject;
	}



	public String getDate() {
		return date;
	}



	public int getIsAttached() {
		return isAttached;
	}



	public String getContentKey() {
		return contentKey;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
}

package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Message implements Serializable {

public Message() {
	super();
}
//get message must init it
	public void init(){
		if(this.messageModeId==null){
			this.isAttached="N";
		}else{
			this.isAttached="Y";
		}
		if(this.createdTime!=null){
			this.createdTimeStr=new SimpleDateFormat("yyyy.MM.dd hh:mm").format(this.createdTime);
			this.createdDateStr=new SimpleDateFormat("yyyy.MM.dd").format(this.createdTime);
		}
	}
	private static final long serialVersionUID = 5644476293509171348L;

	private int id;
	private int senderId;
	private int receiverId;
	private String sender;
	private String subject;
	private String content;
	private Date createdTime;
	private String createdTimeStr;
	private String createdDateStr;
	private String open;
	private String deleted;
	private String isAttached;
	
	//message mode
	//meesage mode -->messageId==this.id
	private Integer messageModeId;
	private Integer playerItemId;
	private String playerItemName;
	
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getIsAttached() {
		return isAttached;
	}
	public void setIsAttached(String isAttached) {
		this.isAttached = isAttached;
	}
	public Integer getPlayerItemId() {
		return playerItemId;
	}
	public void setPlayerItemId(Integer playerItemId) {
		this.playerItemId = playerItemId;
	}
	public String getPlayerItemName() {
		return playerItemName;
	}
	public void setPlayerItemName(String playerItemName) {
		this.playerItemName = playerItemName;
	}
	public Integer getMessageModeId() {
		return messageModeId;
	}
	public void setMessageModeId(Integer messageModeId) {
		this.messageModeId = messageModeId;
	}
	public String getCreatedTimeStr() {
		return createdTimeStr;
	}
	public void setCreatedTimeStr(String createdTimeStr) {
		this.createdTimeStr = createdTimeStr;
	}
	public String getCreatedDateStr() {
		return createdDateStr;
	}
	public void setCreatedDateStr(String createdDateStr) {
		this.createdDateStr = createdDateStr;
	}
}

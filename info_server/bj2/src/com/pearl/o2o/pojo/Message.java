package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.pearl.o2o.utils.ServiceLocator;


public class Message implements Serializable {

public Message() {
	super();
}
//get message must init it
	public void init(){
		if(this.createdTime!=null){
			if(this.playerItemId==null || this.playerItemId==0){
				this.haveAttached="N";
			}else{
				this.haveAttached="Y";
			}
			this.createdTimeStr=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(this.createdTime);
			this.createdDateStr=new SimpleDateFormat("yyyy-MM-dd").format(this.createdTime);
		}
	}
	private static final long serialVersionUID = 5644476293509171348L;

	private int id;
	private int receiverId;
	private String sender;
	private int senderId;
	private String subject;
	private String content;
	private Date createdTime;
	private String createdTimeStr;
	private String createdDateStr;
	private String open;
	private String deleted;
	private int isAttached;
	private String haveAttached;
	private String items="";
	private String itemsUnits="";
	private String itemsUnitTypes="";
//	private int playerItem;
	private List<SysItem> itemList;
	
	//message mode
	//meesage mode -->messageId==this.id
	private Integer messageModeId;
	private Integer playerItemId;
	
	private PlayerItem playerItem;
	
	private String receiver;
	
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
	
	public int getIsAttached() {
		return isAttached;
	}
	public void setIsAttached(int isAttached) {
		this.isAttached = isAttached;
	}
	public Integer getPlayerItemId() {
		return playerItemId;
	}
	public void setPlayerItemId(Integer playerItemId) {
		this.playerItemId = playerItemId;
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
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public PlayerItem getPlayerItem() {
		return playerItem;
	}
	public void setPlayerItem(PlayerItem playerItem) {
		this.playerItem = playerItem;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) throws Exception {
		this.items = items;
		this.itemList = ServiceLocator.updateService.joinMessageItem(this);
	}
	
	public String getItemsUnits() {
		return itemsUnits;
	}
	public void setItemsUnits(String itemsUnits) {
		this.itemsUnits = itemsUnits;
	}
	public String getItemsUnitTypes() {
		return itemsUnitTypes;
	}
	public void setItemsUnitTypes(String itemsUnitTypes) {
		this.itemsUnitTypes = itemsUnitTypes;
	}
	public List<SysItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SysItem> itemList) {
		this.itemList = itemList;
	}
	public String getHaveAttached() {
		return haveAttached;
	}
	public void setHaveAttached(String haveAttached) {
		this.haveAttached = haveAttached;
	}
	
}

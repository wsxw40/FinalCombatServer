package com.pde.uweb.database.cdkey.gamecdkey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.*;

/**
 * @title 		Pojo
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <lifengyang@pde.com>
 * @version		$Id: GameCdKeyDao,v 1.0 2012-10-29 Sean Weng Exp $
 * @create		2012-10-29
 */
public class AbstractGameCdKeyPojo {

    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private long channelId;

    @JsonSerialize(using = ToStringSerializer.class)
    private long gameCdKeyTypeId;

    @JsonSerialize(using = ToStringSerializer.class)
    private long userId;
	
		private String cdKey;
	
		private Date expireDate;
	
		private int enable;
    @JsonIgnore
    private Date createDate;
    @JsonIgnore
    private Date lastModifyDate;
	
	@JsonIgnore
    private long version;
	
	
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
	
		public long getChannelId() {
			return channelId;
		}
		public void setChannelId(long channelId) {
			this.channelId = channelId;
		}
	
		public long getGameCdKeyTypeId() {
			return gameCdKeyTypeId;
		}
		public void setGameCdKeyTypeId(long gameCdKeyTypeId) {
			this.gameCdKeyTypeId = gameCdKeyTypeId;
		}
	
		public long getUserId() {
			return userId;
		}
		public void setUserId(long userId) {
			this.userId = userId;
		}
	
		public String getCdKey() {
			return cdKey;
		}
		public void setCdKey(String cdKey) {
			this.cdKey = cdKey;
		}
	
		public Date getExpireDate() {
			return expireDate;
		}
		public void setExpireDate(Date expireDate) {
			this.expireDate = expireDate;
		}
	
		public int getEnable() {
			return enable;
		}
		public void setEnable(int enable) {
			this.enable = enable;
		}
	
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
	
		public Date getLastModifyDate() {
			return lastModifyDate;
		}
		public void setLastModifyDate(Date lastModifyDate) {
			this.lastModifyDate = lastModifyDate;
		}
	
		public long getVersion() {
			return version;
		}
		public void setVersion(long version) {
			this.version = version;
		}
	}
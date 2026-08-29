package com.pde.uweb.database.ws.channel;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.*;

/**
 * @title 		Pojo
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: ChannelDao,v 1.0 2012-09-06 Sean Weng Exp $
 * @create		2012-09-06
 */
public class AbstractChannelPojo {
	
		/** 渠道表主键 */
        @JsonSerialize(using = ToStringSerializer.class)
        private long channelId;
	
		/** 游戏名称 */
		private String gameName;
	
		/** 游戏区服 */
		private String gameService;
	
		/** 游戏编号 */
		private String gameNumber;
	
		/** 联运合作商id */
		private String partnerId;
	
		/** 代理ip */
		private String proxyId;
	
		/** 代理端口 */
		private int proxyPort;
	
		/** 区服状态 */
		private int status;
	
		/** 创建日期 */
		private Date createDate;
	
		/** 最后修改日期 */
		private Date lastModifyDate;
	
		/** 版本号 */
		private long version;
	
	
		public long getChannelId() {
			return channelId;
		}
		public void setChannelId(long channelId) {
			this.channelId = channelId;
		}
	
		public String getGameName() {
			return gameName;
		}
		public void setGameName(String gameName) {
			this.gameName = gameName;
		}
	
		public String getGameService() {
			return gameService;
		}
		public void setGameService(String gameService) {
			this.gameService = gameService;
		}
	
		public String getGameNumber() {
			return gameNumber;
		}
		public void setGameNumber(String gameNumber) {
			this.gameNumber = gameNumber;
		}
	
		public String getPartnerId() {
			return partnerId;
		}
		public void setPartnerId(String partnerId) {
			this.partnerId = partnerId;
		}
	
		public String getProxyId() {
			return proxyId;
		}
		public void setProxyId(String proxyId) {
			this.proxyId = proxyId;
		}
	
		public int getProxyPort() {
			return proxyPort;
		}
		public void setProxyPort(int proxyPort) {
			this.proxyPort = proxyPort;
		}
	
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
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
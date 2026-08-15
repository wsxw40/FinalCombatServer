package com.pde.uweb.database.partner.partneruser;

import java.util.*;

/**
 * @title 		Pojo
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: PartnerUserDao,v 1.0 2012-12-13 Sean Weng Exp $
 * @create		2012-12-13
 */
public class AbstractPartnerUserPojo {
	
		private long userId;
	
		private String partnerId;
	
		private String gameAccount;
	
		private String password;
	
		private String email;
	
		private int status;
	
		private Date createDate;
	
		private Date lastModifyDate;
	
		private long version;
	
	
		public long getUserId() {
			return userId;
		}
		public void setUserId(long userId) {
			this.userId = userId;
		}
	
		public String getPartnerId() {
			return partnerId;
		}
		public void setPartnerId(String partnerId) {
			this.partnerId = partnerId;
		}
	
		public String getGameAccount() {
			return gameAccount;
		}
		public void setGameAccount(String gameAccount) {
			this.gameAccount = gameAccount;
		}
	
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
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
package com.pde.uweb.database.ma.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

/**
 * @title 		Pojo
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: UsersDao,v 1.0 2012-08-31 Sean Weng Exp $
 * @create		2012-08-31
 */
public class AbstractUserPojo {
		
		/** 用户id */
        @JsonSerialize(using = ToStringSerializer.class)
        private long userId;
	
		/** 用户名 */
		private String userName;
		
		/** 密码 */
		private String password;
	
		/** 真实姓名 */
		private String idName;
	
		/** 身份证号 */
		private String idNumber;
	
		/** 用户状态 */
		private int status;
	
		/** 余额 */
		private int balance;
		
		/** 创建日期 */
		private Date createDate;
		
		/** 最后修改日期 */
		private Date lastModifyDate;
	
		/** 版本号 */
		private long version;
		
		/** 电子邮箱 */
		private String email;
		
		/** 手机号 */
		private String mobile;
		
		/** 邮箱状态 */
		private int emailStatus;
		
		/** 手机状态 */
		private int mobileStatus;
		
		/** question id */
		private long questionId;
		
		/** 安全问题的答案 */
		private String answer;
		
		
		public long getUserId() {
			return userId;
		}
		public void setUserId(long userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}	
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getIdName() {
			return idName;
		}
		public void setIdName(String idName) {
			this.idName = idName;
		}	
		public String getIdNumber() {
			return idNumber;
		}
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}	
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}	
		public int getBalance() {
			return balance;
		}
		public void setBalance(int balance) {
			this.balance = balance;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public int getEmailStatus() {
			return emailStatus;
		}
		public void setEmailStatus(int emailStatus) {
			this.emailStatus = emailStatus;
		}
		public int getMobileStatus() {
			return mobileStatus;
		}
		public void setMobileStatus(int mobileStatus) {
			this.mobileStatus = mobileStatus;
		}
		public long getQuestionId() {
			return questionId;
		}
		public void setQuestionId(long questionId) {
			this.questionId = questionId;
		}
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		
	}
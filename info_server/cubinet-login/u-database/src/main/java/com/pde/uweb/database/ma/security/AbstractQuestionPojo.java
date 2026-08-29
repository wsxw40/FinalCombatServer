package com.pde.uweb.database.ma.security;

import java.util.*;

/**
 * @title 		Pojo
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: QuestionDao,v 1.0 2012-09-25 Sean Weng Exp $
 * @create		2012-09-25
 */
public class AbstractQuestionPojo {
	
		private long questionId;
	
		private String questionInfo;
	
		private int orderById;
	
		private int status;
	
		private Date createDate;
	
		private Date lastModifyDate;
	
		private long version;
	
	
		public long getQuestionId() {
			return questionId;
		}
		public void setQuestionId(long questionId) {
			this.questionId = questionId;
		}
	
		public String getQuestionInfo() {
			return questionInfo;
		}
		public void setQuestionInfo(String questionInfo) {
			this.questionInfo = questionInfo;
		}
	
		public int getOrderById() {
			return orderById;
		}
		public void setOrderById(int orderById) {
			this.orderById = orderById;
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
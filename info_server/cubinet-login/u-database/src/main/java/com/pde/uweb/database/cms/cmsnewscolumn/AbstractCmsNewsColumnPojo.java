package com.pde.uweb.database.cms.cmsnewscolumn;

import java.util.*;

/**
 * @title 		Pojo
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: CmsNewsColumnDao,v 1.0 2012-09-27 Sean Weng Exp $
 * @create		2012-09-27
 */
public class AbstractCmsNewsColumnPojo {
	
		private long newsColumnId;
	
		private String columnName;
		
		private int status;
	
		private Date createDate;
	
		private Date lastModifyDate;
	
		private long version;
	
	
		public long getNewsColumnId() {
			return newsColumnId;
		}
		public void setNewsColumnId(long newsColumnId) {
			this.newsColumnId = newsColumnId;
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
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getColumnName() {
			return columnName;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
	}
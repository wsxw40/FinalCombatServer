package com.pde.uweb.database.cms.cmsnews;

import java.util.*;

/**
 * @title 		Pojo
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: CmsNewsDao,v 1.0 2012-09-27 Sean Weng Exp $
 * @create		2012-09-27
 */
public class AbstractCmsNewsPojo {
	
		/** 新闻编号 */
		private long newsId;
	
		/** 栏目编号 */
		private long newsColumnId;
	
		/** 标题 */
		private String headline;
	
		/** 副标题 */
		private String subhead;
	
		/** 新闻内容 */
		private String content;
	
		/** 作者 */
		private String author;
	
		/** 置顶级别 */
		private int topLevel;
	
		/** 显示优先级 */
		private int priorityLevel;
	
		/** 是否头条 */
		private int headlineStatus;
	
		/** 新闻状态 */
		private int status;

		/** 新闻类型 */
		private int type;

		/** 创建日期 */
		private Date createDate;
	
		/** 最后修改日期 */
		private Date lastModifyDate;
	
		/** 版本号 */
		private long version;
		
		/** 缩略图 */
		private String thumbnails;
	
		/** 返回新闻编号 */
		public long getNewsId() {
			return newsId;
		}
		public void setNewsId(long newsId) {
			this.newsId = newsId;
		}
	
		/** 返回栏目编号 */
		public long getNewsColumnId() {
			return newsColumnId;
		}
		public void setNewsColumnId(long newsColumnId) {
			this.newsColumnId = newsColumnId;
		}
		/** 返回标题 */
		public String getHeadline() {
			return headline;
		}
		public void setHeadline(String headline) {
			this.headline = headline;
		}
		/** 返回副标题 */
		public String getSubhead() {
			return subhead;
		}
		public void setSubhead(String subhead) {
			this.subhead = subhead;
		}
		/** 返回新闻内容 */
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		/** 返回作者 */
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		/** 返回置顶级别 */
		public int getTopLevel() {
			return topLevel;
		}
		public void setTopLevel(int topLevel) {
			this.topLevel = topLevel;
		}
		/** 返回显示优先级 */
		public int getPriorityLevel() {
			return priorityLevel;
		}
		public void setPriorityLevel(int priorityLevel) {
			this.priorityLevel = priorityLevel;
		}
		/** 返回是否头条 */
		public int getHeadlineStatus() {
			return headlineStatus;
		}
		public void setHeadlineStatus(int headlineStatus) {
			this.headlineStatus = headlineStatus;
		}
		/** 返回新闻状态 */
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		/** 返回新闻类型 */
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		/** 返回创建日期 */
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		/** 返回最后修改日期 */
		public Date getLastModifyDate() {
			return lastModifyDate;
		}
		public void setLastModifyDate(Date lastModifyDate) {
			this.lastModifyDate = lastModifyDate;
		}
		/** 返回版本号 */
		public long getVersion() {
			return version;
		}
		public void setVersion(long version) {
			this.version = version;
		}
		public String getThumbnails() {
			return thumbnails;
		}
		public void setThumbnails(String thumbnails) {
			this.thumbnails = thumbnails;
		}
	}
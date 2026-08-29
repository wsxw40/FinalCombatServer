package com.pde.uweb.cms.dto;

import com.pde.infor.common.utils.ValidateParaAnnotation;

/** 
 * 修改新闻请求对象
 * 
 * @author Sean.Weng
 */
public class ModifyNewsRequestDto {

	private long newsId;
	
	@ValidateParaAnnotation(errorKey = "headlineError", errorMsg = "正标题必填", isNull = false, maxLength=50)
	private String headline;

	@ValidateParaAnnotation(errorKey = "subheadError", errorMsg = "副标题必填", isNull = false, maxLength=150)
	private String subhead;
	
	@ValidateParaAnnotation(errorKey = "contentError", errorMsg = "新闻内容必填", isNull = false)
	private String content;
	
	/** 栏目编号 */
	private long newsColumnId;
	
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
	
	/** 缩略图 */
	private String thumbnails;

	
	public long getNewsId() {
		return newsId;
	}
	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getSubhead() {
		return subhead;
	}
	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getNewsColumnId() {
		return newsColumnId;
	}
	public void setNewsColumnId(long newsColumnId) {
		this.newsColumnId = newsColumnId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getTopLevel() {
		return topLevel;
	}
	public void setTopLevel(int topLevel) {
		this.topLevel = topLevel;
	}
	public int getPriorityLevel() {
		return priorityLevel;
	}
	public void setPriorityLevel(int priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	public int getHeadlineStatus() {
		return headlineStatus;
	}
	public void setHeadlineStatus(int headlineStatus) {
		this.headlineStatus = headlineStatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(String thumbnails) {
		this.thumbnails = thumbnails;
	}

}

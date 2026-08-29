package com.pde.uweb.cms.dto;

import com.pde.infor.common.utils.ValidateParaAnnotation;

/** 新闻发布请求对象 */
public class CreateNewsRequestDto {

	@ValidateParaAnnotation(errorKey = "headlineError", errorMsg = "标题必填", isNull = false, maxLength=50)
	private String headline;

	@ValidateParaAnnotation(errorKey = "subheadError", errorMsg = "副标题必填", isNull = false, maxLength=150)
	private String subhead;
	
	@ValidateParaAnnotation(errorKey = "newsColumnIdError", errorMsg = "栏目必选", isNull = false)
	private long newsColumnId;
	
	private String content;
	
	private int status;
	
	private String author;
	
	private int type;
	
	/** 显示优先级 */
	private int priorityLevel;
	
	/** 缩略图 */
	private String thumbnails;

	
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
	public long getNewsColumnId() {
		return newsColumnId;
	}
	public void setNewsColumnId(long newsColumnId) {
		this.newsColumnId = newsColumnId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public int getPriorityLevel() {
		return priorityLevel;
	}
	public void setPriorityLevel(int priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
}

package com.pde.uweb.database.cms.cmsnews;

/**
 * 新闻资讯查询过滤条件对象
 */
public class CmsNewsSearchFilter {

	/** 标题 */
	private String headline;

	/** 作者 */
	private String author;

	/** 新闻状态 */
	private int status;
	
	/** 新闻类型 */
	private int type;

	/** 创建日期--起始日期 */
	private String fromDate;

	/** 创建日期--结束日期 */
	private String toDate;
	
	/** 分页参数--从第几行开始 */
	private int beginRow;
	
	/** 分页参数--行数 */
	private int rowCount;

	
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public int getBeginRow() {
		return beginRow;
	}
	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
}

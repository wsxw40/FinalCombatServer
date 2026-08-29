package com.pde.uweb.admin.vo.content;

/**
 * 内容发布dto
 * 
 * @author Huanggang
 * 
 */
public class CMSContentRequestVo {

	/**
	 * 发布标题
	 */
	private String cmsTitle;

	/**
	 * 类型
	 */
	private String cmsType;

	/**
	 * 摘要
	 */
	private String cmsDigest;

	/**
	 * 具体内容
	 */
	private String cmsContent;

	/**
	 * @return the cmsTitle
	 */
	public String getCmsTitle() {
		return cmsTitle;
	}

	/**
	 * @param cmsTitle
	 *            the cmsTitle to set
	 */
	public void setCmsTitle(String cmsTitle) {
		this.cmsTitle = cmsTitle;
	}

	/**
	 * @return the cmsType
	 */
	public String getCmsType() {
		return cmsType;
	}

	/**
	 * @param cmsType
	 *            the cmsType to set
	 */
	public void setCmsType(String cmsType) {
		this.cmsType = cmsType;
	}

	/**
	 * @return the cmsDigest
	 */
	public String getCmsDigest() {
		return cmsDigest;
	}

	/**
	 * @param cmsDigest
	 *            the cmsDigest to set
	 */
	public void setCmsDigest(String cmsDigest) {
		this.cmsDigest = cmsDigest;
	}

	/**
	 * @return the cmsContent
	 */
	public String getCmsContent() {
		return cmsContent;
	}

	/**
	 * @param cmsContent
	 *            the cmsContent to set
	 */
	public void setCmsContent(String cmsContent) {
		this.cmsContent = cmsContent;
	}

}

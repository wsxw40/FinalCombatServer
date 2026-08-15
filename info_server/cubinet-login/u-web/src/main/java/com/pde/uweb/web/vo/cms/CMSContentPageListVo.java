/**
 * 
 */
package com.pde.uweb.web.vo.cms;

/**
 * @author Huanggang
 *
 */
public class CMSContentPageListVo {
	
	/**
	 * 文章标题
	 */
	private String cmsTitle;
	
	/**
	 * 文章摘要
	 */
	private String cmsDigest;
	
	
	/**
	 * 内容编号
	 */
	private long cmsId;

	/**
	 * @return the cmsTitle
	 */
	public String getCmsTitle() {
		return cmsTitle;
	}

	/**
	 * @param cmsTitle the cmsTitle to set
	 */
	public void setCmsTitle(String cmsTitle) {
		this.cmsTitle = cmsTitle;
	}

	/**
	 * @return the cmsDigest
	 */
	public String getCmsDigest() {
		return cmsDigest;
	}

	/**
	 * @param cmsDigest the cmsDigest to set
	 */
	public void setCmsDigest(String cmsDigest) {
		this.cmsDigest = cmsDigest;
	}

	/**
	 * @return the cmsId
	 */
	public long getCmsId() {
		return cmsId;
	}

	/**
	 * @param cmsId the cmsId to set
	 */
	public void setCmsId(long cmsId) {
		this.cmsId = cmsId;
	}
	
	
	

}

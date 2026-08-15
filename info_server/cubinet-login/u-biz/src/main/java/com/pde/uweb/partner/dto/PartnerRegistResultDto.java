package com.pde.uweb.partner.dto;

public class PartnerRegistResultDto {

	/** 注册成功与否 */
	private boolean isRegistSuccess;

	/** 注册失败原因 */
	private String registError;
	
	/** 用户id */
	private String partnerUid;

	public boolean isRegistSuccess() {
		return isRegistSuccess;
	}
	public void setRegistSuccess(boolean isRegistSuccess) {
		this.isRegistSuccess = isRegistSuccess;
	}
	public String getRegistError() {
		return registError;
	}
	public void setRegistError(String registError) {
		this.registError = registError;
	}
	public String getPartnerUid() {
		return partnerUid;
	}
	public void setPartnerUid(String partnerUid) {
		this.partnerUid = partnerUid;
	}
}

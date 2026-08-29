package com.pde.uweb.partner.dto;

import com.pde.infor.common.utils.ValidateParaAnnotation;

/** 合作商用户管理--登录请求对象 */
public class PartnerLoginRequestDto {
	
	@ValidateParaAnnotation(errorKey = "partnerIdError", errorMsg = "partnerId is null", isNull = false)
	private String partnerId;
	
	@ValidateParaAnnotation(errorKey = "gameAccountError", errorMsg = "game account is null", isNull = false)
	private String gameAccount;
	
	@ValidateParaAnnotation(errorKey = "pwError", errorMsg = "pw is null", isNull = false)
	private String password;
	
	@ValidateParaAnnotation(errorKey = "signatureError", errorMsg = "signature is null", isNull = false)
	private String signature;

	private String userData;

	
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getGameAccount() {
		return gameAccount;
	}
	public void setGameAccount(String gameAccount) {
		this.gameAccount = gameAccount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getUserData() {
		return userData;
	}
	public void setUserData(String userData) {
		this.userData = userData;
	}
}

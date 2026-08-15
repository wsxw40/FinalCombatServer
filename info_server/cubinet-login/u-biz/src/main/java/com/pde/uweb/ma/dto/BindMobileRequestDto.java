package com.pde.uweb.ma.dto;

import com.pde.infor.common.utils.ValidateParaAnnotation;

/** 绑定手机请求对象 */
public class BindMobileRequestDto {

	@ValidateParaAnnotation(errorKey = "error", errorMsg = "userId必传", isNull = false)
	private long userId;
	
	@ValidateParaAnnotation(errorKey = "mobileNumberError", errorMsg = "手机号必填", isNull = false)
	private String mobileNumber;
	
	@ValidateParaAnnotation(errorKey = "mobileCodeError", errorMsg = "短信验证码必填", isNull = false)
	private String mobileCode;

	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMobileCode() {
		return mobileCode;
	}
	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

}

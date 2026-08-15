package com.pde.uweb.ma.dto;

import com.pde.infor.common.utils.ValidateParaAnnotation;

/** 防沉迷认证请求对象 */
public class AddicateRequestDto {

	@ValidateParaAnnotation(errorKey = "error", errorMsg = "userId必传", isNull = false)
	private long userId;

	@ValidateParaAnnotation(errorKey = "idNameError", errorMsg = "真实姓名必填", isNull = false)
	private String idName;

	@ValidateParaAnnotation(errorKey = "idNumberError", errorMsg = "身份证必填", isNull = false)
	private String idNumber;

	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

}

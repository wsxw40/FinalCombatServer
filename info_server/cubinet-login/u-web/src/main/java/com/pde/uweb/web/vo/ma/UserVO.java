/**
 * 
 */
package com.pde.uweb.web.vo.ma;

import java.io.Serializable;

/**
 * 页面上传递的user对象
 * 
 * @author Huanggang
 * 
 */
public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3495951366825636346L;

	private long userId;

	private String userName;

	private String idName;

	private String idNumber;

	private String email;

	private String mobile;

	private int emailStatus;

	private int mobileStatus;
	
	private int status;
	
	private long questionId;
	
	private String answer;

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(int emailStatus) {
		this.emailStatus = emailStatus;
	}

	public int getMobileStatus() {
		return mobileStatus;
	}

	public void setMobileStatus(int mobileStatus) {
		this.mobileStatus = mobileStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}

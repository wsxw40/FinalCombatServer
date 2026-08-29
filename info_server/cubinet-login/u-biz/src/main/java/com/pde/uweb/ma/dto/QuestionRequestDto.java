package com.pde.uweb.ma.dto;

import com.pde.infor.common.utils.ValidateParaAnnotation;

/** 安全问题设置请求对象 */
public class QuestionRequestDto {

	@ValidateParaAnnotation(errorKey = "error", errorMsg = "userId必传", isNull = false)
	private long userId;

	@ValidateParaAnnotation(errorKey = "qIdError", errorMsg = "安全问题必选", isNull = false)
	private long questionId;

	@ValidateParaAnnotation(errorKey = "answerError", errorMsg = "答案必填", isNull = false)
	private String answer;


	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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

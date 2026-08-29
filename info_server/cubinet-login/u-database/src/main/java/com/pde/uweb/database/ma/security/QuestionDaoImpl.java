package com.pde.uweb.database.ma.security;

import java.util.List;

/**
 * @title 		自定义dao方法接口的默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: QuestionDaoImpl,v 1.0 2012-09-25 Sean Weng Exp $
 * @create		2012-09-25
 */
public class QuestionDaoImpl extends AbstractQuestionDao {
	
	/**
	 * 获得所有安全问题记录
	 * 
	 * @return 返回 QuestionPojo List，目前只包含2个字段：question_id 和 question_info
	 */
	@SuppressWarnings("unchecked")
	public List<QuestionPojo> getAllQuestions() {
		
		Object result = this.getSqlMapClientTemplate().queryForList("Question.selectAll");
		return (result != null) ? (List<QuestionPojo>) result : null;
	}
	
	/**
	 * 获得指定用户对应的安全问题详述
	 */
	public String getQuestionInfo(long userId) {
		
		Object result = this.getSqlMapClientTemplate().queryForObject("Question.selectInfoById", userId);
		return (result != null) ? (String) result : null;
	}
}
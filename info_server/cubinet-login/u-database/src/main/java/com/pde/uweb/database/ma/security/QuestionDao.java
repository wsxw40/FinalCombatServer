package com.pde.uweb.database.ma.security;

import java.util.List;

import com.pde.infor.common.dao.EntityDao;

/**
 * @title 		自定义dao方法接口
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: QuestionDao,v 1.0 2012-09-25 Sean Weng Exp $
 * @create		2012-09-25
 */
public interface QuestionDao extends EntityDao<QuestionPojo> {

	/**
	 * 获得所有安全问题记录
	 * 
	 * @return 返回 QuestionPojo List，目前只包含2个字段：question_id 和 question_info
	 */
	public List<QuestionPojo> getAllQuestions();

	/**
	 * 获得指定的安全问题详述
	 */
	public String getQuestionInfo(long questionId);

}
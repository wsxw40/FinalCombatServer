package com.pde.uweb.database.ma.security;

/**
 * @title 		 QuestionDao访问接口默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: QuestionDao,v 1.0 2012-09-25 Sean Weng Exp $
 * @create		2012-09-25
 */
public class QuestionPojo extends AbstractQuestionPojo {

	/**
	 * 安全问题状态--有效
	 */
	public static final int STATUS_ACTIVE = Integer.valueOf(1);
	
	/**
	 * 安全问题状态--无效
	 */
	public static final int STATUS_INACTIVE = Integer.valueOf(0);
	
}
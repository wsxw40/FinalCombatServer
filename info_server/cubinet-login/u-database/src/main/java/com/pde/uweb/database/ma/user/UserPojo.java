package com.pde.uweb.database.ma.user;

/**
 * @title 		 UsersDao访问接口默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: UsersDao,v 1.0 2012-08-31 Sean Weng Exp $
 * @create		2012-08-31
 */
public class UserPojo extends AbstractUserPojo {

	/** 用户状态--已认证 */
	public static final int STATUS_ACTIVE = Integer.valueOf(1);
	
	/** 用户状态--未认证 */
	public static final int STATUS_INACTIVE = Integer.valueOf(0);
	
	/** 邮箱状态--邮箱已绑定 */
	public static final int STATUS_EMAIL_BIND_YES = Integer.valueOf(1);
	
	/** 邮箱状态--邮箱未绑定 */
	public static final int STATUS_EMAIL_BIND_NO = Integer.valueOf(0);
	
	/** 手机号状态--手机号已绑定 */
	public static final int STATUS_MOBILE_BIND_YES = Integer.valueOf(1);
	
	/** 手机号状态--手机号未绑定 */
	public static final int STATUS_MOBILE_BIND_NO = Integer.valueOf(0);
	
	
}
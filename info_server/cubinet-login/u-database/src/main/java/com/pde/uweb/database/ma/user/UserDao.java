package com.pde.uweb.database.ma.user;

import com.pde.infor.common.dao.EntityDao;;

/**
 * @title 		自定义dao方法接口
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: UsersDao,v 1.0 2012-08-31 Sean Weng Exp $
 * @create		2012-08-31
 */
public interface UserDao extends EntityDao<UserPojo> {

	/** 根据登录名获得用户信息 */
	public UserPojo getUserByLoginName(String loginName);
	
	/** 
	 * 根据登录名获得 user_id
	 * 
	 * @return 没有记录返回 0
	 */
	public long getUidByLoginName(String loginName);
	
	/** 
	 * 根据email获得 user_id
	 * 
	 * @return 没有记录返回 0
	 */
	public long getUidByEmail(String email);
}
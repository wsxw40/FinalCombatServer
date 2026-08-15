package com.pde.uweb.database.ma.user;

import java.util.HashMap;
import java.util.Map;

/**
 * @title 		自定义dao方法接口的默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: UsersDaoImpl,v 1.0 2012-08-31 Sean Weng Exp $
 * @create		2012-08-31
 */
public class UserDaoImpl extends AbstractUserDao {

	/** 根据登录名获得用户信息 */
	public UserPojo getUserByLoginName(String loginName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", loginName);
		Object result = this.getSqlMapClientTemplate().queryForObject("User.selectByLoginName", map);		
		return (result != null) ? (UserPojo)result : null;
	}
	
	/** 
	 * 根据登录名获得 user_id
	 * 
	 * @return 没有记录返回 0
	 */
	public long getUidByLoginName(String loginName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", loginName);
		Object result = this.getSqlMapClientTemplate().queryForObject("User.selectUidByLoginName", map);		
		return (result != null) ? (Long)result : Long.valueOf(0);
	}

	/** 
	 * 根据email获得 user_id
	 * 
	 * @return 没有记录返回 0
	 */
	public long getUidByEmail(String email) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		Object result = this.getSqlMapClientTemplate().queryForObject("User.selectUidByEmail", map);		
		return (result != null) ? (Long)result : Long.valueOf(0);
	}

}
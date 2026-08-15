package com.pde.uweb.database.ma.user;

import java.util.*;

import com.pde.infor.common.dao.AbstractEntityDao;

/**
 * @title 		数据库访问抽象类
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: AbstractUserDao,v 1.0 2012-08-31 Sean Weng Exp $
 * @create		2012-08-31
 */
public abstract class AbstractUserDao extends AbstractEntityDao<UserPojo> implements UserDao {

	/**
	 * 插入
	 *
	 * @return 返回新增记录的id
	 */
	@Override
	public Long add(UserPojo user) {
		if (user==null)
			return Long.valueOf(0);
		long newId = user.getUserId();
		this.getSqlMapClientTemplate().insert("AbstractUser.insert", user);
		return newId;
	}

	/**
	 * 更新
	 *
	 * @return 更新是否成功
	 */
	@Override
	public boolean update(UserPojo user) {
		if (user == null)
			return false;
		return this.getSqlMapClientTemplate().update("AbstractUser.update", user) == Integer.valueOf(1);
	}
	
	/**
	 * 更新pojo中非空属性对应的字段值（pojo中必须有主键值和version值）
	 *
	 * @return 更新是否成功
	 */
	@Override
	public boolean updateForField(UserPojo user) {
		if (user == null)
			return false;
		return this.getSqlMapClientTemplate().update("AbstractUser.updateForField", user) == Integer.valueOf(1);
	}
	
	/**
	 * 根据pojo删除
	 *
	 * @return 删除是否成功
	 */
	@Override
	public boolean delete(UserPojo user) {
		if (user == null)
			return false;
		return this.delete(user.getUserId(), user.getVersion());
	}

	/**
	 * 根据主键和版本号删除
	 * 
	 * @param id 主键
	 * @param version 记录的版本
	 * @return 删除是否成功
	 */
	@Override
	public boolean delete(long id, long version) {
		if (id <= Long.valueOf(0))
			return false;
		if (version < Long.valueOf(1)) // version默认从1开始
			return false;

		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", String.valueOf(id));
		map.put("version", String.valueOf(version));
		return this.getSqlMapClientTemplate().delete("AbstractUser.delete", map) == Integer.valueOf(1);
	}

	/**
	 * 通过主键查找,返回pojo对象
	 */
	@Override
	public UserPojo select(long id) {
		if (id <= Long.valueOf(0))
			return null;
		Object result = this.getSqlMapClientTemplate().queryForObject("AbstractUser.select", id);
		return (result != null) ? (UserPojo)result : null;
	}

	/**
	 * 根据主键获得记录的version
	 *
	 * @return 若查不到记录，返回0
	 */
	@Override
	public long getVersion(long id) {
		if (id  <= Long.valueOf(0))
			return Long.valueOf(0);

		Object result = this.getSqlMapClientTemplate().queryForObject("AbstractUser.getVersion", id);
		return (result != null) ? (Long)result : Long.valueOf(0);
	}

}

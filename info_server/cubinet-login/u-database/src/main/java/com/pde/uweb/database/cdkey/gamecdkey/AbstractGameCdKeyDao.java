package com.pde.uweb.database.cdkey.gamecdkey;

import java.util.*;

import com.pde.infor.common.dao.AbstractEntityDao;
import com.pde.infor.common.utils.KeyUtil;

/**
 * @title 		数据库访问抽象类
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		lifengyang <lifengyang@pde.com>
 * @version		$Id: AbstractGameCdKeyDao,v 1.0 2012-09-26 lifengyang Exp $
 * @create		2012-09-26
 */
public abstract class AbstractGameCdKeyDao extends AbstractEntityDao<GameCdKeyPojo> implements GameCdKeyDao {

	/**
	 * 插入
	 *
	 * @return 返回新增记录的id
	 */
	public Long add(GameCdKeyPojo gamecdkey) {
		if (gamecdkey==null)
			return Long.valueOf(0);
		long newId = KeyUtil.generateDBKey();
        gamecdkey.setId(newId);
		this.getSqlMapClientTemplate().insert("AbstractGameCdKey.insert", gamecdkey);
		// last_insert_id()只能取sequence，所以返回值从pojo里取
		return newId;
	}

	/**
	 * 更新
	 *
	 * @return 更新是否成功
	 */
	public boolean update(GameCdKeyPojo gamecdkey) {
		if (gamecdkey == null)
			return false;
		return this.getSqlMapClientTemplate().update("AbstractGameCdKey.update", gamecdkey) == Integer.valueOf(1);
	}
	
	/**
	 * 更新pojo中非空属性对应的字段值（pojo中必须有主键值和version值）
	 *
	 * @return 更新是否成功
	 */
	public boolean updateForField(GameCdKeyPojo gamecdkey) {
		if (gamecdkey == null)
			return false;
		return this.getSqlMapClientTemplate().update("AbstractGameCdKey.updateForField", gamecdkey) == Integer.valueOf(1);
	}
	
	/**
	 * 根据pojo删除
	 *
	 * @return 删除是否成功
	 */
	public boolean delete(GameCdKeyPojo gamecdkey) {
		if (gamecdkey == null)
			return false;
		return this.delete(gamecdkey.getId(), gamecdkey.getVersion());
	}

	/**
	 * 根据主键和版本号删除
	 * 
	 * @param id 主键
	 * @param version 记录的版本
	 * @return 删除是否成功
	 */
	public boolean delete(long id, long version) {
		if (id <= Long.valueOf(0))
			return false;
		if (version < Long.valueOf(1)) // version默认从1开始
			return false;

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", String.valueOf(id));
		map.put("version", String.valueOf(version));
		return this.getSqlMapClientTemplate().delete("AbstractGameCdKey.delete", map) == Integer.valueOf(1);
	}

	/**
	 * 通过主键查找,返回pojo对象
	 */
	public GameCdKeyPojo select(long id) {
		if (id <= Long.valueOf(0))
			return null;
		Object result = this.getSqlMapClientTemplate().queryForObject("AbstractGameCdKey.select", id);
		return (result != null) ? (GameCdKeyPojo)result : null;
	}

	/**
	 * 根据主键获得记录的version
	 *
	 * @return 若查不到记录，返回0
	 */
	public long getVersion(long id) {
		if (id  <= Long.valueOf(0))
			return Long.valueOf(0);

		Object result = this.getSqlMapClientTemplate().queryForObject("AbstractGameCdKey.getVersion", id);
		return (result != null) ? (Long)result : Long.valueOf(0);
	}

}

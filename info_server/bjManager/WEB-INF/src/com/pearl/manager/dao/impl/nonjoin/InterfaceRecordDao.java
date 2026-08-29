package com.pearl.manager.dao.impl.nonjoin;

import java.sql.SQLException;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.manager.pojo.InterfaceRecord;

public class InterfaceRecordDao extends SqlMapClientDaoSupport{
	public int insert(InterfaceRecord record) throws SQLException{
		return (Integer) this.getSqlMapClient().insert("InterfaceRecord.insert",record);
	}
	public void update(InterfaceRecord record) throws SQLException{
		this.getSqlMapClient().update("InterfaceRecord.update",record);
	}
	public void delete(InterfaceRecord record) throws SQLException{
		this.getSqlMapClient().delete("InterfaceRecord.delete",record);
	}
	public InterfaceRecord getByOrderId(String orderId) throws SQLException{
		return (InterfaceRecord) this.getSqlMapClient().queryForObject("InterfaceRecord.getByOrderId",orderId);
	}
}

package com.pearl.o2o.dao.impl.nonjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.XunleiOrderLog;


public class XunleiOrderLogDao extends SqlMapClientDaoSupport{
	
	public static final int type_recharge = 1;
	public static final int type_chargeback = 2;
	public static final int type_paytobevip = 3;
	
	
	@SuppressWarnings("unchecked")
	public XunleiOrderLog createXunleiOrderLog(XunleiOrderLog xunleiPayLog) throws DataAccessException {
		int id = (Integer) this.getSqlMapClientTemplate().insert("XunleiOrderLog.insert", xunleiPayLog);
		xunleiPayLog.setId(id);
		return xunleiPayLog;
	}
	
	public int duplicateXunleiOrder(String orderId,String userId){
		Map<String, String> map=new HashMap<String, String>();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return (Integer) this.getSqlMapClientTemplate().queryForObject("XunleiOrderLog.duplicateXunleiOrder", map);
	}
	public XunleiOrderLog getXunleiOrderLog(String orderId,String userId){
		Map<String, String> map=new HashMap<String, String>();
		map.put("orderId", orderId);
		map.put("userId", userId);
		return (XunleiOrderLog) this.getSqlMapClientTemplate().queryForObject("XunleiOrderLog.select", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<XunleiOrderLog> getAddXunleiOrderLogList(int playerId){
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("playerId", playerId);
		return this.getSqlMapClientTemplate().queryForList("XunleiOrderLog.selectAddXunleiOrderForList", map);
	}
	public List<XunleiOrderLog> getReduceXunleiOrderLogList(int playerId){
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("playerId", playerId);
		return this.getSqlMapClientTemplate().queryForList("XunleiOrderLog.selectReduceXunleiOrderForList", map);
	}
}

package com.pearl.o2o.dao.impl.nonjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.PayLog;

public class PayLogDao  extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public List<PayLog> getpayloListByPlayerId(int playerId){
		Map<String,Integer> paramMap=new HashMap<String, Integer>();
		paramMap.put("playerId", playerId);
		return this.getSqlMapClientTemplate().queryForList("PayLog.selectByPlayerId",paramMap);
	}
	public void createPayLog(PayLog payLog){
		this.getSqlMapClientTemplate().insert("PayLog.insert",payLog);
	}

}

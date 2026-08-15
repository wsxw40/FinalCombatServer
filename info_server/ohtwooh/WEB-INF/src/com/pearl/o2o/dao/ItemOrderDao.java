package com.pearl.o2o.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.SDOItemOrderPojo;
import com.snda.services.oa.client.bean.SDOItemOrder;

public class ItemOrderDao extends SqlMapClientDaoSupport{
	
	
	public void create(SDOItemOrderPojo pojo)  throws DataAccessException {
		this.getSqlMapClientTemplate().insert("Order.insert",pojo);
	}
	
	public SDOItemOrderPojo getByOrderId(String orderId) throws DataAccessException, Exception{
		return  (SDOItemOrderPojo) this.getSqlMapClientTemplate().queryForObject("Order.selectOrderByOrderId", orderId);
	}
	
	public void update(SDOItemOrder order)  throws DataAccessException {
		this.getSqlMapClientTemplate().update("Order.update", order.getSDOItemOrderPojo());
	}
	
	public void updateOrderState(String orderId, SDOItemOrder.OrderState state){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("orderId", orderId);
		paramMap.put("state", state.name());
		this.getSqlMapClientTemplate().update("Order.updateOrderState",paramMap);
	}
}

package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.utils.ComparatorUtil;

public class PaymentDao extends BaseMappingDao {
	
	public Map<Integer, Payment> getPaymentMap(int itemId) throws Exception{
		return queryMappingBeanMapByRelatedId(Payment.class, itemId);
	}
	public List<Payment> getPaymentListById(int itemId) throws Exception{
		Map<Integer, Payment> paymentMap=getPaymentMap(itemId);
		List<Payment> paymentList=new ArrayList<Payment>(paymentMap.values());
		Collections.sort(paymentList, new ComparatorUtil.IdComparatorClass());
		return paymentList;
		
	}
	public int insertPayment(Payment payment) throws Exception{
		return insertObjIntoDBAndCache(payment, payment.getItemId());
	}
	
	public void updatePayment(Payment payment)throws Exception{
		updateMappingBeanInCache(payment, payment.getItemId());
	}
	public void deletePayment(Payment payment)throws Exception{
		deleteObjFromDBAndCache(payment, payment.getItemId());
	}
}

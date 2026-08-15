package com.pde.info.analyser.service.impl;

import java.util.List;

import com.pde.info.analyser.dao.PaymentDao;
import com.pde.info.analyser.pojo.Payment;
import com.pde.info.analyser.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

	private PaymentDao paymentDao;
	
	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	@Override
	public List<Payment> getCardsUnitPrice() {
		return paymentDao.getCardsUnitPrice();
	}

}

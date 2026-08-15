package com.pde.info.analyser.service.impl;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.dao.MedalExchangeDao;
import com.pde.info.analyser.pojo.MedalExchange;
import com.pde.info.analyser.pojo.MedalExchangeStatistic;
import com.pde.info.analyser.service.MedalExchangeService;

public class MedalExchangeServiceImpl implements MedalExchangeService {
	
	private MedalExchangeDao medalExchangeDao;
	
	
	

	public void setMedalExchangeDao(MedalExchangeDao medalExchangeDao) {
		this.medalExchangeDao = medalExchangeDao;
	}




	@Override
	public void addExchangeInfo(MedalExchange medalCost) {
		// TODO Auto-generated method stub
		medalExchangeDao.addExchangeInfo(medalCost);	
	}




	@Override
	public List<MedalExchangeStatistic> getStatistic(Map<String, String> param) {
		// TODO Auto-generated method stub
		List<MedalExchangeStatistic> list=medalExchangeDao.getStatistic(param);
	
		return list;
	}

}

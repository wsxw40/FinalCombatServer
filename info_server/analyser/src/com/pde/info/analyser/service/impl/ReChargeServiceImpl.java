package com.pde.info.analyser.service.impl;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.dao.ReChargeDao;
import com.pde.info.analyser.pojo.ReChargeInfo;
import com.pde.info.analyser.pojo.ReChargeStatistic;
import com.pde.info.analyser.service.ReChargeService;

public class ReChargeServiceImpl implements ReChargeService{
	private ReChargeDao reChargeDao;

	@Override
	public void addReChargeInfo(ReChargeInfo reChargeInfo) {
		// TODO Auto-generated method stub
		reChargeDao.addReChargeInfo(reChargeInfo);
	}

	public void setReChargeDao(ReChargeDao reChargeDao) {
		this.reChargeDao = reChargeDao;
	}

	@Override
	public List<ReChargeStatistic> getStatistic(Map<String, String> param) {
		List<ReChargeStatistic> list=reChargeDao.getStatistic(param);
		return list;
	}

	
}

package com.pde.info.analyser.service.impl;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.dao.GPointTotalDao;
import com.pde.info.analyser.pojo.GPointTotalStatistic;
import com.pde.info.analyser.service.GPointTotalService;

public class GPointTotalServiceImpl implements GPointTotalService {

	GPointTotalDao gPointTotalDao;
	
	@Override
	public void addGPointTotal(GPointTotalStatistic gPonitTotal) {
		gPointTotalDao.addGPointTotal(gPonitTotal);
	}

	@Override
	public List<GPointTotalStatistic> getStatistic(Map<String, String> param) {
		return gPointTotalDao.getStatistic(param);
	}

	public void setGPointTotalDao(GPointTotalDao gPointTotalDao) {
		this.gPointTotalDao = gPointTotalDao;
	}

}

package com.pde.info.analyser.service.impl;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.dao.LuckyPackageDao;
import com.pde.info.analyser.pojo.LuckyPackage;
import com.pde.info.analyser.pojo.LuckyPackageStatistic;
import com.pde.info.analyser.service.LuckyPackageService;

public class LuckyPackageServiceImpl implements LuckyPackageService {
	
    private LuckyPackageDao luckyPackageDao;

    public void setLuckyPackageDao(LuckyPackageDao luckyPackageDao) {
        this.luckyPackageDao = luckyPackageDao;
    }

    public void addLuckyPackage(LuckyPackage luckyPackage) {
		luckyPackageDao.addLuckyPackage(luckyPackage);
	}
    
    public List<LuckyPackageStatistic> getStatistic(Map<String, String> param) {
    	return luckyPackageDao.getStatistic(param);
    }

}

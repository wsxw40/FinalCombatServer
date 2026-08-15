package com.pde.info.analyser.service.impl;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.dao.SysLevelDao;
import com.pde.info.analyser.service.SysLevelService;

public class SysLevelServiceImpl implements SysLevelService {

	private SysLevelDao sysLevelDao;
	
	public void setSysLevelDao(SysLevelDao sysLevelDao) {
		this.sysLevelDao = sysLevelDao;
	}
	
	@Override
	public List<String> getAllMapNames() {
		return sysLevelDao.getAllMapNames();
	}
	
	@Override
	public List<String> getLevelId(Map<String, String> param) {
		return sysLevelDao.getLevelId(param);
	}

}

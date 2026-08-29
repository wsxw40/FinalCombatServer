package com.pde.info.analyser.service.impl;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.dao.CharacterKDDao;
import com.pde.info.analyser.pojo.CharacterKD;
import com.pde.info.analyser.pojo.CharacterKDStatistic;
import com.pde.info.analyser.service.CharacterKDService;

public class CharacterKDServiceImpl implements CharacterKDService {
	
    private CharacterKDDao characterKDDao;

	public void setCharacterKDDao(CharacterKDDao characterKDDao) {
		this.characterKDDao = characterKDDao;
	}

	
	public void addCharacterKD(CharacterKD characterKD) {
		characterKDDao.addCharacterKD(characterKD);
	}

	public List<CharacterKDStatistic> getStatistic(Map<String, String> param) {
		return characterKDDao.getStatistic(param);
	}
}

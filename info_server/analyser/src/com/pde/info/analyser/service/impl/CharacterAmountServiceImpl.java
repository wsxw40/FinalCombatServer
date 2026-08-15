package com.pde.info.analyser.service.impl;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.dao.CharacterAmountDao;
import com.pde.info.analyser.pojo.CharacterAmount;
import com.pde.info.analyser.pojo.CharacterAmountStatistic;
import com.pde.info.analyser.service.CharacterAmountService;

public class CharacterAmountServiceImpl implements CharacterAmountService {
	
    private CharacterAmountDao characterAmountDao;

    public void setCharacterAmountDao(CharacterAmountDao characterAmountDao) {
        this.characterAmountDao = characterAmountDao;
    }

	public void addCharacterAmount(CharacterAmount characterAmount) {
		characterAmountDao.addCharacterAmount(characterAmount);
	}

	public List<CharacterAmountStatistic> getStatistic(Map<String, String> param) {
		return characterAmountDao.getStatistic(param);
	}
}

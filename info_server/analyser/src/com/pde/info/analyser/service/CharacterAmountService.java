package com.pde.info.analyser.service;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.CharacterAmount;
import com.pde.info.analyser.pojo.CharacterAmountStatistic;


public interface CharacterAmountService {

	public void addCharacterAmount(CharacterAmount characterAmount);

	public List<CharacterAmountStatistic> getStatistic(Map<String, String> param);

}

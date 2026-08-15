package com.pde.info.analyser.service;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.CharacterKD;
import com.pde.info.analyser.pojo.CharacterKDStatistic;


public interface CharacterKDService {

	public void addCharacterKD(CharacterKD characterKD);

	public List<CharacterKDStatistic> getStatistic(Map<String, String> param);

}

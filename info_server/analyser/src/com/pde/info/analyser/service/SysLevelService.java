package com.pde.info.analyser.service;

import java.util.List;
import java.util.Map;

public interface SysLevelService {

	public List<String> getAllMapNames();
	
	/**
	 *  根据 "type 模式" 和 "displayName 地图名称" 获得地图id 
	 */
	public List<String> getLevelId(Map<String, String> param);

}

package com.pde.info.analyser.dao;

import java.util.List;
import java.util.Map;

public interface SysLevelDao {

	public  List<String> getAllMapNames();
	public List<String> getLevelId(Map<String, String> param);

}

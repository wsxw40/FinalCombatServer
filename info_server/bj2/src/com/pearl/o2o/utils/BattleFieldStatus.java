package com.pearl.o2o.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 记录资源争夺战交火信息
 * 
 * @author zhang.li
 * 
 */
public class BattleFieldStatus implements Serializable {
	private static final long serialVersionUID = 3420479016078958811L;
	private List<String> battleStartKeys = new ArrayList<String>();

	public List<String> getBattleStartKeys() {
		return battleStartKeys;
	}

	public void setBattleStartKeys(List<String> battleStartKeys) {
		this.battleStartKeys = battleStartKeys;
	}

	public void addNewBattle(String key) {
		this.battleStartKeys.add(key);
	}

}

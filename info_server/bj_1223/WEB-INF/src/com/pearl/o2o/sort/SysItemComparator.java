package com.pearl.o2o.sort;

import java.util.Comparator;

import com.pearl.o2o.pojo.SysItem;


public class SysItemComparator implements Comparator<SysItem> {

	@Override
	public int compare(SysItem s1, SysItem s2) {
		int typeCom=s1.getType().compareTo(s2.getType());
		if(typeCom!=0){
			return typeCom;
		}else return s2.getRareLevel()-s1.getRareLevel();
	}

}

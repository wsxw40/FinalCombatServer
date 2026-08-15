package com.pearl.manager.utils;

import java.util.Comparator;

import com.pearl.manager.pojo.BaseMappingBean;


@SuppressWarnings("unchecked")
public class IdComparatorClass implements Comparator {
	public int compare(Object arg0, Object arg1) {
		Integer ch1 = ((BaseMappingBean)arg0).getId();
		Integer ch2 = ((BaseMappingBean)arg1).getId();
		return ch1.compareTo(ch2);
	}
}

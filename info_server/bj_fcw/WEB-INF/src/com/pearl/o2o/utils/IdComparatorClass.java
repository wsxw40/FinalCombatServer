package com.pearl.o2o.utils;

import java.util.Comparator;

import com.pearl.o2o.pojo.BaseMappingBean;


public class IdComparatorClass implements Comparator<Object> {
	@Override
	public int compare(Object arg0, Object arg1) {
		Integer ch1 = ((BaseMappingBean<?>)arg0).getId();
		Integer ch2 = ((BaseMappingBean<?>)arg1).getId();
		return ch1.compareTo(ch2);
	}
}

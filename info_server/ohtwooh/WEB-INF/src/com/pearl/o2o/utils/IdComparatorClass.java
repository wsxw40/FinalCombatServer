package com.pearl.o2o.utils;

import java.util.Comparator;

import com.pearl.o2o.pojo.BasePojo;


@SuppressWarnings("unchecked")
public class IdComparatorClass implements Comparator {
	@Override
	public int compare(Object arg0, Object arg1) {
		int ch1 = ((BasePojo)arg0).getId();
		int ch2 = ((BasePojo)arg1).getId();
		if (ch1>=ch2) {
			return 1;
		} else {
			return 0;
		}
	}
}

package com.pearl.o2o.sort;

import java.util.Comparator;

import com.pearl.o2o.pojo.SysChest;

public class LuckyPackageComparator implements Comparator<SysChest> {

	@Override
	public int compare(SysChest s1, SysChest s2) {
		int compare_rare_level = compareByDesc(s1.getSysItem().getRareLevel(), s2.getSysItem().getRareLevel());
		if (compare_rare_level != 0)
			return compare_rare_level;
		int compare_type = compareByAsc(s1.getSysItem().getType(), s2.getSysItem().getType());
		if (compare_type != 0)
			return compare_type;
		int compare_sub_type = compareByAsc(s1.getSysItem().getSubType(), s2.getSysItem().getSubType());
		if (compare_sub_type != 0)
			return compare_sub_type;
		int compare_m_type = compareByAsc(s1.getSysItem().getMType(), s2.getSysItem().getMType());
		if (compare_m_type != 0)
			return compare_m_type;
		int compare_character_id = compareByAsc(s1.getSysItem().getCharacterId(), s2.getSysItem().getCharacterId());
		if (compare_character_id != 0)
			return compare_character_id;
		int compare_id = compareByAsc(s1.getSysItem().getId(), s2.getSysItem().getId());
		if (compare_id != 0)
			return compare_id;
		int compare_num = compareByDesc(s1.getNumber(), s2.getNumber());
		return compare_num;
		// int typeCom =
		// s1.getSysItem().getType().compareTo(s2.getSysItem().getType());
		//
		// if (typeCom != 0) {
		// return typeCom;
		// } else if (s1.getSysItem().getType() ==
		// Constants.DEFAULT_MATERIAL_TYPE) {
		// int mTypeCom =
		// s1.getSysItem().getMType().compareTo(s2.getSysItem().getMType());
		// if (mTypeCom != 0) {
		// return mTypeCom;
		// } else {
		// int mValueCom =
		// s1.getSysItem().getMValue().compareTo(s2.getSysItem().getMValue());
		// if (mValueCom != 0) {
		// return mValueCom;
		// } else
		// return s2.getNumber() - s1.getNumber();
		// }
		// } else
		// return s1.getSysItemId() - s2.getSysItemId();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int compareByDesc(Comparable value1, Comparable value2) {
		if (null != value1 && null != value2) {
			return value2.compareTo(value1);
		}
		return 0;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int compareByAsc(Comparable value1, Comparable value2) {
		if (null != value1 && null != value2) {
			return value1.compareTo(value2);
		}
		return 0;
	}
}

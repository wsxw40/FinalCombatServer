package com.pearl.fcw.gm.pojo.columnDescriptor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.gm.pojo.WSysItemGunProperty;
import com.pearl.fcw.utils.StringUtil;

/**
 * SysItemGunProperty表的特殊字段描述
 */
public class WSysItemGunPropertyColumnDescriptor extends ColumnDescriptor<WSysItemGunProperty> {
	@Override
	public WSysItemGunProperty get(WSysItemGunProperty m) {
		if (null == m) {
			return m;
		}
		if (!StringUtil.isEmpty(m.getMultiType())) {
			Map<Integer, List<Integer>> map = Stream.of(m.getMultiType().split(";")).map(p -> {
				List<String> list = Stream.of(p.split(",")).collect(Collectors.toList());
				return list;
			}).collect(Collectors.toMap(p -> Integer.parseInt(p.get(0)), p -> {
				return Stream.of(p.get(1).split("\\|")).map(Integer::parseInt).collect(Collectors.toList());
			}));
			m.getMultiTypeMap().putAll(map);
		}
		return m;
	}

	@Override
	public void set(WSysItemGunProperty m) {
	}

}

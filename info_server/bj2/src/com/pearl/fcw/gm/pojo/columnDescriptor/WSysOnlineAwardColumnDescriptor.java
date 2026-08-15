package com.pearl.fcw.gm.pojo.columnDescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.gm.pojo.WSysOnlineAward;
import com.pearl.fcw.utils.StringUtil;

/**
 * SysOnlineAward表的特殊字段描述
 */
public class WSysOnlineAwardColumnDescriptor extends ColumnDescriptor<WSysOnlineAward> {
	@Override
	public WSysOnlineAward get(WSysOnlineAward m) {
		if (null == m) {
			return m;
		}
		if (!StringUtil.isEmpty(m.getMultiType())) {
			Map<Integer, List<Integer>> map = Stream.of(m.getMultiType().split(";")).map(p -> {
				List<String> list = Stream.of(p.split(",")).collect(Collectors.toList());
				return list;
			}).collect(Collectors.toMap(p -> Integer.parseInt(p.get(0)), p -> {
				if (p.size() < 2) {
					return new ArrayList<>();
				}
				return Stream.of(p.get(1).split("\\|")).map(Integer::parseInt).collect(Collectors.toList());
			}));
			m.getMultiTypeMap().putAll(map);
		}
		return m;
	}

	@Override
	public void set(WSysOnlineAward m) {
	}

}

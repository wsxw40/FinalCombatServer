package com.pearl.fcw.gm.pojo.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pearl.fcw.proto.enums.EItemSubType;

/**
 * SysItem的Type和subType字段的关系
 */
public class ItemSubType {
    /**
	 * 每个子类型占位空间
	 */
    public static final int SEP = 1000;

    public static Map<Integer, List<Integer>> values() {
        Map<Integer, List<Integer>> map=new HashMap<>();
        List<Integer> list = Stream.of(EItemSubType.values()).filter(p -> !EItemSubType.UNRECOGNIZED.equals(p) && !EItemSubType.ITEMSUBTYPE_NONE.equals(p)).map(p -> p.getNumber())
                .collect(Collectors.toList());
        for (int i : list) {
            if (!map.containsKey(i / SEP)) {
                map.put(i / SEP, new ArrayList<>());
            }
            map.get(i / SEP).add(i % 1000);
        }
        return map;
    }
}

package com.pearl.fcw.info.core.persistence.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.fcw.info.core.persistence.BaseEntity;

public class EntityUtils {

    public static final Comparator<BaseEntity> DEFAULT_ENTITY_COMPARATOR = new Comparator<BaseEntity>() {
        @Override
        public int compare(BaseEntity o1, BaseEntity o2) {
            if (o1.getId() < o2.getId()) {
                return -1;
            } else if (o1.getId() > o2.getId()) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    public static <T extends BaseEntity> Map<Integer, T> toPKMap(Collection<T> entities) {
        Map<Integer, T> result = new HashMap<Integer, T>();
        for (T t : entities) {
            result.put(t.getId(), t);
        }
        return result;
    }

    public static <T extends BaseEntity> List<T> sortById(List<T> entities) {
        Collections.sort(entities, DEFAULT_ENTITY_COMPARATOR);
        return entities;
    }
}

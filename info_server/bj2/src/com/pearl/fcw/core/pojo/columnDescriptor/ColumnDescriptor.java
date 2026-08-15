package com.pearl.fcw.core.pojo.columnDescriptor;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;

/**
 * 数据库特殊字段描述<br/>
 * 通常在读取数据库数据后，把对应的String字段按照某些字符串分割为List或者Map；写入数据库前，List或者Map再转为String<br/>
 * 或者有些字段在某些条件下需要重新计算和封装
 * @param <M> @BaseEntity
 */
public abstract class ColumnDescriptor<M extends BaseEntity> {
	//储存ColumnDescriptor的子类对应的BaseEntity实现类
    private static final Map<Class<? extends BaseEntity>, ColumnDescriptor<? extends BaseEntity>> entities = new HashMap<>();

    /**
	 * 读取数据库后，把对应的String字段按照某些字符串分割为List或者Map
	 * @param m @BaseEntity
	 * @return
	 */
    public abstract M get(M m);

    /**
	 * 写入数据库前，List或者Map转为String
	 * @param m @BaseEntity
	 */
    public abstract void set(M m);

    /**
	 * 获取BaseEntity实现类对应的ColumnDescriptor子类。没有对应的ColumnDescriptor子类返回null
	 * @param cls @BaseEntity.class
	 * @return
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <M extends BaseEntity> ColumnDescriptor apply(Class<M> cls) {
        if (entities.isEmpty()) {
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
			//查找项目中ColumnDescriptor的子类
            List<TypeFilter> filters = Arrays.asList(new AssignableTypeFilter(ColumnDescriptor.class));
            scanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
                for (TypeFilter f : filters) {
                    if (!f.match(metadataReader, metadataReaderFactory)) {
                        return false;
                    }
                }
                return true;
            });
            Set<BeanDefinition> bds = scanner.findCandidateComponents("com.pearl.fcw");
            List<Class<?>> classes = bds.stream().map(bd -> {
                try {
                    return Class.forName(bd.getBeanClassName());
                } catch (Exception e) {
                    return null;
                }
            }).collect(Collectors.toList());
			//查找项目中ColumnDescriptor的子类所对应的BaseEntity实现类
            classes.forEach(p -> {
                if (p.equals(ColumnDescriptor.class)) {
                    return;
                }
                try {
                    Class<M> mCls = (Class<M>) ((ParameterizedType) p.getGenericSuperclass()).getActualTypeArguments()[0];
                    entities.put(mCls, (ColumnDescriptor<? extends BaseEntity>) p.newInstance());
                } catch (Exception e) {
                }
            });
        }
		while (null != cls && !cls.isAnnotationPresent(GoSchema.class) && !cls.equals(DmModel.class)) {
            cls = (Class<M>) cls.getSuperclass();
        }
        return entities.getOrDefault(cls, null);
    }
}

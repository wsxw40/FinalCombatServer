package com.pearl.fcw.info.bean;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pearl.fcw.info.core.persistence.utils.ClassUtils;
import com.pearl.fcw.info.lobby.pojo.BasePojo;

@Scope("singleton")
@Component(value="entities")
public class Entities extends ArrayList<Class<?>> {
	private static final long serialVersionUID = 1L;

	public Entities() {
//		add(AsShard.class);
//		add(User.class);
		addAll(ClassUtils.getClasses(BasePojo.class.getPackage().getName()).stream()
				.filter(c->c.isAnnotationPresent(javax.persistence.Entity.class))
				.collect(Collectors.toList()));
	}
}

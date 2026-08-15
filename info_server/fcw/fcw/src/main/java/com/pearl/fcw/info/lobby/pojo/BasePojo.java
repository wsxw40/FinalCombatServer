package com.pearl.fcw.info.lobby.pojo;

import javax.persistence.MappedSuperclass;

import com.pearl.fcw.info.core.persistence.BaseEntity;

@MappedSuperclass
public abstract class BasePojo implements BaseEntity, Cloneable {

	private static final long serialVersionUID = 5979630953714473567L;

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

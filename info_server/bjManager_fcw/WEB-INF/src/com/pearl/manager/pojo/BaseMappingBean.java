package com.pearl.manager.pojo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public abstract class BaseMappingBean<T> implements Serializable,Cloneable {

	protected Integer id;
	//返回数据库分表的表后缀
	public String getTableSuffix(){
	    return "";
	}
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		  return ReflectionToStringBuilder.toString(this);
	}
}

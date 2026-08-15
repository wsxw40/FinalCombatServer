package com.pearl.o2o.pojo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public abstract class BaseMappingBean<T> implements Serializable,Cloneable {
	private static final long serialVersionUID = 5214162456426179355L;
	public static final int DEFAULT_EXPR = 24 * 60 * 60;
	public static final int DEFAULT_SYN_INTERVAL = 1;

	protected Integer id=0;

	private int expr = DEFAULT_EXPR;//expr time in memcache

	private int synInterval = DEFAULT_SYN_INTERVAL ; // the interval seconds between each syn //TODO


	//返回数据库分表的表后缀
	public String getTableSuffix(){
	    return "";
	}



	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getExpr(){
		return expr;
	}

	public int getSynInterval() {
		return synInterval;
	}

	public void setSynInterval(int synInterval) {
		this.synInterval = synInterval;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+ ": id="+this.id;
//		return ReflectionToStringBuilder.toString(this);
	}
	
	public String toStringCompelet(){
		return ReflectionToStringBuilder.toString(this);
	}
}

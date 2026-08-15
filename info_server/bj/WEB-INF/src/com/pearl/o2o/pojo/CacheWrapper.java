package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author Timon
 *
 * @param <T>
 */
public class CacheWrapper<T> implements Serializable {
	private static final long serialVersionUID = 8935624322287607719L;
	private final String key;
	
	private boolean isDirty ;//几乎不用了
	private long lastSynTimeStampSeconds; //几乎不用了
	private long cas = -1;
	
	private Map<Integer,BaseMappingBean<T>> mappingBeans;
	
	
	public CacheWrapper(String key, BaseMappingBean<T> bean){
		this.key = key;
		this.isDirty = false;
		this.lastSynTimeStampSeconds = System.currentTimeMillis()/1000;
		this.mappingBeans = new HashMap<Integer,BaseMappingBean<T>>();
		mappingBeans.put(bean.getId(), bean);
	}
	
	public CacheWrapper(String key, Map<Integer,BaseMappingBean<T>> map ){
		this.key = key;
		this.isDirty = false;
		this.lastSynTimeStampSeconds = System.currentTimeMillis()/1000;
		this.mappingBeans = map;
	}
	
	public String getKey() {
		return key;
	}
	public boolean isDirty() {
		return isDirty;
	}
	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}
	
	public long getLastSynTimeStampSeconds() {
		return lastSynTimeStampSeconds;
	}
	public void setLastSynTimeStampSeconds(long lastSynTimeStampSeconds) {
		this.lastSynTimeStampSeconds = lastSynTimeStampSeconds;
	}
	
	public Map<Integer, BaseMappingBean<T>> getMappingBeans() {
		return mappingBeans;
	}

	public void setMappingBeans(Map<Integer, BaseMappingBean<T>> mappingBeans) {
		this.mappingBeans = mappingBeans;
	}
	
	public BaseMappingBean<T> getSingleDBMappingBean(){
		if (mappingBeans == null || mappingBeans.isEmpty()) {
			return null;
		}
		if(mappingBeans.values().iterator()==null){
			return null;
		}
		return mappingBeans.values().iterator().next();
	}
	
	public void addMappingBean(BaseMappingBean<T> mappingBean){
		mappingBeans.put(mappingBean.getId(), mappingBean);
	}
	
	public long getCas() {
		return cas;
	}

	public void setCas(long cas) {
		this.cas = cas;
	}

	public int getSynIntervalSeconds(){
		BaseMappingBean<T> bean = getSingleDBMappingBean();
		if (bean != null) {
			return bean.getSynInterval();
		}else {
			return BaseMappingBean.DEFAULT_SYN_INTERVAL;
		}
	}
	public int getExprTimeInCache(){
		BaseMappingBean<T> bean = getSingleDBMappingBean();
		if (bean != null) {
			return bean.getExpr();
		}else {
			return  BaseMappingBean.DEFAULT_EXPR;
		}
	}

	@Override
	public String toString() {
		  return ReflectionToStringBuilder.toString(this);
	}
	
	
}

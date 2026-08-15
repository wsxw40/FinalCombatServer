package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.pearl.o2o.pojo.DBMappingBean.Status;
import com.pearl.o2o.utils.Constants;
@Deprecated
public class CacheWrapperMetaDeprecated implements Serializable{
	private static final long serialVersionUID = 8935624322287607719L;
	private final String key;
	private final String orginKey;
	private boolean isDirty = false;
	private long lastSynTimeStampSeconds;
	private int synInterval;
	
	private Map<Integer,Status> mappingBeansStatus = new HashMap<Integer,Status>();
	
	public static final String METAKEY_PREFIX = "META_";
	
	public static String getMetaKey(String realCacheKey){
		return METAKEY_PREFIX + realCacheKey;
	}
	
	public <T> CacheWrapperMetaDeprecated(CacheWrapper<T> cacheWrapper){
		this.orginKey = cacheWrapper.getKey();
		this.key = CacheWrapperMetaDeprecated.getMetaKey(orginKey);
		BaseMappingBean<T> bb=cacheWrapper.getSingleDBMappingBean();
		if(bb==null){
			this.synInterval =Constants.DEFAULT_BASEMAPPINGBEAB_SYNINTERVAL;
		}else{
			this.synInterval = bb.getSynInterval();
		}
		
		this.lastSynTimeStampSeconds = cacheWrapper.getLastSynTimeStampSeconds();
		this.isDirty = cacheWrapper.isDirty();
		
		for(Map.Entry<Integer, BaseMappingBean<T>> entry : cacheWrapper.getMappingBeans().entrySet()){
			mappingBeansStatus.put(entry.getKey(), Status.FRESH);
		}
	}

	public void updateMappingBeanStatus(int id, Status newStatus){
		mappingBeansStatus.put(id, newStatus);
	}
	
	
	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public void setLastSynTimeStampSeconds(long lastSynTimeStampSeconds) {
		this.lastSynTimeStampSeconds = lastSynTimeStampSeconds;
	}

	public String getKey() {
		return key;
	}

	public boolean isDirty() {
		return isDirty;
	}

	public long getLastSynTimeStampSeconds() {
		return lastSynTimeStampSeconds;
	}

	public int getSynInterval() {
		return synInterval;
	}

	public Map<Integer, Status> getMappingBeansStatus() {
		return mappingBeansStatus;
	}

	public String getOrginKey() {
		return orginKey;
	}
	
	/**
	 * 更新对应的MappingBean的状态，如果该状态为DELETED，则直接从此状态MAP中移去
	 * @param changedStatus
	 */
	public void updateMappingBeanStatusByMap(Map<Integer,Status> changedStatus){
		if (changedStatus == null) {
			return ;
		}
		for (Map.Entry<Integer, Status> entry : changedStatus.entrySet()) {
			if (entry.getValue() == Status.DELETED) {
				mappingBeansStatus.remove(entry.getKey());
			}else{
				mappingBeansStatus.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	@Override
	public String toString() {
		  return ReflectionToStringBuilder.toString(this);
	}
	
}

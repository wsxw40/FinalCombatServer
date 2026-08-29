package com.pearl.o2o.utils;

import com.pearl.o2o.pojo.DBMappingBean;

public class DaoCacheUtil {
	
	public static final String CACHEPREFIX = "DC_";
	public static final String DirtyMappingBeanKey = "DMB_";
	public static final String DirtyMappingBeanKeysPattern = "DMB_*";
	public static final String DUMPDirtyMappingBeanKey = "DUMP_";
	
	
	public static <T> String oCacheKey(DBMappingBean<T> obj, int id){
		return oCacheKey(obj.getClass(),id);
	}
	
	
	
	public static <T> String oCacheKey(Class<T> clazz, int id){
		return new StringBuilder(CACHEPREFIX).append(clazz.getSimpleName())
				.append(Constants.DELIMITER).append(id).toString();
	}
	public static <T> String oCacheKey(String clazz, String id){
		return new StringBuilder(CACHEPREFIX).append(clazz)
				.append(Constants.DELIMITER).append(id).toString();
	}
	public static <T> String oCacheKey(Class<T> clazz){
		return new StringBuilder(CACHEPREFIX).append(clazz.getSimpleName()).toString();
	}
	
	public static String oDirtyMappingBeanKey(String clazz){
		return DirtyMappingBeanKey+clazz;
	}
	public static String oDirtyMappingBeanValue(String fId,String id){
		return fId+Constants.DELIMITER+id;
	}
	public static String oDUMPDirtyMappingBeanKey(String clazz){
		return DUMPDirtyMappingBeanKey+clazz;
	}
}

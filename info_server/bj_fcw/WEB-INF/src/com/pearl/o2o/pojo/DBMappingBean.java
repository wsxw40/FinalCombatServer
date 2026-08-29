package com.pearl.o2o.pojo;

public interface DBMappingBean<T> {
	public enum Status{
		NEW,FRESH,CHANGED,TEMP,DELETED
	}
	/*public enum TypeInCache{
		OBJECT,MAP,ALL
	}*/
	public int getId();
	public void setId(int id);
	
	public Status getDBMappingStatus();
	public void setDBMappingStatus(Status newStatus);
	
	
}

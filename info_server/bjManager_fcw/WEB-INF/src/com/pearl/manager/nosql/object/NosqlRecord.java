package com.pearl.manager.nosql.object;

public abstract class  NosqlRecord {
	public static final String DELEMETER = "|"; 
	
	public enum NoSqlDataType{
		LIST, STRING, SET;
	}
	
	protected abstract NoSqlDataType getDataTypeKey();
	
	protected abstract String getRecordType();
	
	protected abstract String getKey();
	
	protected abstract String generateNosqlPlainStr();
	
	//protected abstract NosqlRecord getFromNosqlPlainStr(String str);
}
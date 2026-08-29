package com.pearl.nosql;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

public interface NoSql {
	public List<String> getQueue(String key) throws TimeoutException, Exception;
	public List<String> getQueue(String key, int length) throws TimeoutException, Exception;
	public Set<String> getKeysByPattern(String pattern)throws Exception;
	
	
	public void rpush(String key, String value ) throws Exception;
	
	public void batchIncr(List<String> keys)throws Exception;
	public void batchIncrBy(Map<String,Integer> map)throws Exception;
	
	public void addToQueue(String listKey, String value, int maxLength) throws TimeoutException, Exception;
	public void set(String key, String value) throws Exception;
	public void set(String key, List<String> list) throws Exception;
	
	public String get(String key) throws Exception;
	public List<String> mGet(List<String> keys) throws Exception;
	
	//end should greater than start
	public List<String> lrange(String key, int start, int end) throws Exception;
	public long llen(String key) throws Exception;
	
	
	public void incr(String key) throws Exception;
	
	//life cycle
	public void start();
	public void shutdown();
	
	public boolean ping() throws Exception;
	
	public void delete(String key)throws Exception;
	public void delete(Collection<String> keys) throws Exception;
	public void deleteAll(String pattern) throws Exception;
	
	public void setHost(String host);

	public void setPort(int port);
	
}

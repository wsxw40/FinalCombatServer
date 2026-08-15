package com.pearl.o2o.nosql.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;

import com.pearl.o2o.nosql.NoSql;

public class FakeNosql implements NoSql {
	private static List<String> empty = new ArrayList<String>();
	
	@Override
	public List<String> getQueue(String key) throws TimeoutException, Exception {
		return empty;
	}

	@Override
	public void addToQueue(String listKey, String value, int length)
			throws TimeoutException, Exception {
	}

	@Override
	public void start() {

	}

	@Override
	public void shutdown() {

	}

	@Override
	public boolean ping() throws Exception {
		return true;
	}

	@Override
	public void deleteAll(String pattern) throws Exception {
		
	}

	@Override
	public void setHost(String host) {
		
	}

	@Override
	public void setPort(int port) {
		
	}

	@Override
	public void set(String key, String value) throws Exception {
	}

	@Override
	public String get(String key) throws Exception {
		return null;
	}

	@Override
	public void delete(String key) throws Exception {
		
	}

	@Override
	public List<String> getQueue(String key, int length)
			throws TimeoutException, Exception {
		return null;
	}


	@Override
	public void set(String key, List<String> list) throws Exception {
		
	}

	@Override
	public void incr(String key) throws Exception {
		
	}

	@Override
	public Set<String> getKeysByPattern(String pattern) throws Exception {
		return null;
	}

	@Override
	public void batchIncr(List<String> keys) throws Exception {
		
	}

	@Override
	public void batchIncrBy(Map<String, Integer> map) throws Exception {
		
	}

	@Override
	public List<String> lrange(String key, int start, int length)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long llen(String key) throws Exception {
		// TODO Auto-generated method stub
		return 0L;
	}

	@Override
	public List<String> mGet(List<String> keys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Collection<String> keys) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rpush(String key, String value) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void lpop(String listKey) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decr(String key, int decr) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zAdd(String key, double score, String member) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> zRange(String key, int start, int end) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long zRank(String key, String member) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void zRemRangeByRank(String key, int start, int end)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zRemRangeByScore(String key, double min, double max)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long zCard(String key) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double zScore(String key, String member) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, int start, int end)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void zunionstore(String dstkey, ZParams params, String... sets)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String rename(String oldkey, String newkey) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sAdd(String key, String member) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> sMembers(String key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String key) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, String> hgetAll(String key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> keys(String pattern) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void zRem(String key, String member) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromList(String key, int count, String member)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void appendToQueue(String listKey, String value, int length)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> revRangeSortedSet(String key, int start, int end)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long revRankInSortedSet(String key, String member) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String hashGet(String key, String field) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hashSet(String key, String field, String value)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Tuple> revRangeWithScores(String key, int start, int end)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void hashdel(String key, String field) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long hashlen(String key) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}

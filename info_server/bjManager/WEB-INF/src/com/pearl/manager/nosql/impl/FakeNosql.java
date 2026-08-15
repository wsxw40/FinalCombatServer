package com.pearl.manager.nosql.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;

import com.pearl.manager.nosql.NoSql;

public class FakeNosql implements NoSql {
	private static List<String> empty = new ArrayList<String>();
	
	public List<String> getQueue(String key) throws TimeoutException, Exception {
		return empty;
	}

	public void addToQueue(String listKey, String value, int length)
			throws TimeoutException, Exception {
	}

	public void start() {
	}

	public void shutdown() {
	}

	public boolean ping() throws Exception {
		return true;
	}

	public void deleteAll(String pattern) throws Exception {
	}

	public void setHost(String host) {
	}

	public void setPort(int port) {
	}

	public void set(String key, String value) throws Exception {
	}

	public String get(String key) throws Exception {
		return null;
	}

	public void delete(String key) throws Exception {
	}

	public List<String> getQueue(String key, int length)
			throws TimeoutException, Exception {
		return null;
	}


	public void set(String key, List<String> list) throws Exception {
	}

	public void incr(String key) throws Exception {
	}

	public Set<String> getKeysByPattern(String pattern) throws Exception {
		return null;
	}

	public void batchIncr(List<String> keys) throws Exception {
	}

	public void batchIncrBy(Map<String, Integer> map) throws Exception {
	}

	public List<String> lrange(String key, int start, int length)
			throws Exception {
		return null;
	}

	public long llen(String key) throws Exception {
		return 0L;
	}

	public List<String> mGet(List<String> keys) throws Exception {
		return null;
	}

	public void delete(Collection<String> keys) throws Exception {
	}

	public void rpush(String key, String value) throws Exception {
	}

	public void decr(String key, int decr) throws Exception {
	}

	public void zAdd(String key, double score, String member) throws Exception {
	}

	public Set<String> zRange(String key, int start, int end) throws Exception {
		return null;
	}

	public long zRank(String key, String member) throws Exception {
		return 0;
	}

	public void zRemRangeByRank(String key, int start, int end)
			throws Exception {
	}

	public void zRemRangeByScore(String key, double min, double max)
			throws Exception {
		
	}

	public long zCard(String key) throws Exception {
		return 0;
	}

	public double zScore(String key, String member) throws Exception {
		return 0;
	}

	public Set<Tuple> zrangeWithScores(String key, int start, int end)
			throws Exception {
		return null;
	}

	public void zunionstore(String dstkey, ZParams params, String... sets)
			throws Exception {
	}

	public String rename(String oldkey, String newkey) throws Exception {
		return null;
	}

	public Long sAdd(String key, String member) throws Exception {
		return null;
	}

	public Set<String> sMembers(String key) throws Exception {
		return null;
	}

	public boolean exists(String key) throws Exception {
		return false;
	}

	public Map<String, String> hgetAll(String key) throws Exception {
		return null;
	}

	public Set<String> keys(String pattern) throws Exception {
		return null;
	}

	public void zRem(String key, String member) throws Exception {
		
	}
	
}

package com.pearl.manager.nosql.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;

import com.pearl.manager.nosql.NoSql;

public class Redis implements NoSql {
	private static final Logger LOG = LoggerFactory.getLogger(Redis.class);
	
	static GenericObjectPool.Config cfg = new GenericObjectPool.Config();
	static{
		cfg.maxActive = 100;
		cfg.maxWait = 2 * 1000;//time out
		cfg.timeBetweenEvictionRunsMillis = 5 * 60 * 1000;//connection will be checked per 2minutes
		cfg.minEvictableIdleTimeMillis = 10 * 60 * 1000;// an object will be dropped if it's idle last 10mins
		cfg.testWhileIdle = true;
	}
	
	private JedisPool pool;
	//private static final int QUEUE_SIZE = 50;
	
	private String host;
	private int port;
	
	public void init(){//this method should be called in spring
		LOG.info("launch jedis " + host + ":" + port);
		pool = new JedisPool(cfg, host, port);
		LOG.info("jedis init successfully");
	}
	
	/*private void doAndRetry() throws Exception{
		Jedis jedis = pool.getResource();
		try{
			
		}catch(Exception e){
			pool.returnBrokenResource(jedis);
			logger.error("redis error: " + e);
			throw e;
		}
	}*/

	
	
	
	
	@Override
	public List<String> getQueue(String key) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			long length = jedis.llen(key);
			List<String> result = jedis.lrange(key, 0, (int) length);
			if (result == null) {
				result = new ArrayList<String>();
			}
			pool.returnResource(jedis);
			return result;
		}catch(Exception e){
			pool.returnBrokenResource(jedis);
			LOG.error("redis error: " + e);
			throw e;
		}
	}
	
	@Override
	public List<String> getQueue(String key, int length)
			throws TimeoutException, Exception {
		Jedis jedis = pool.getResource();
		try{
			List<String> result = jedis.lrange(key, 0, length);
			pool.returnResource(jedis);
			return result;
		}catch(Exception e){
			pool.returnBrokenResource(jedis);
			LOG.error("redis error: " + e);
			throw e;
		}
	}
	
	@Override
	public void addToQueue(String listKey, String value, int length) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			long currentLength = jedis.lpush(listKey, value);
			if (currentLength > length){
				for (int i = 0; i < currentLength - length; i++) {//rpop n elements, n = (currentLength - max length)
					jedis.rpop(listKey);
				}
			}
			pool.returnResource(jedis);
		}catch(Exception e){
			pool.returnBrokenResource(jedis);
			LOG.error("redis error: " + e);
			throw e;
		}
	}

	@Override
	public void start() {
	}

	@Override
	public void shutdown() {
		try {
			pool.destroy();
			LOG.info("redis destory successfully");
		} catch (Exception e) {
			LOG.error("redis pool destory fail " + e);
		}
	}

	@Override
	public boolean ping() throws Exception  {
		Jedis jedis = pool.getResource();
		try{
			String s = jedis.ping();
			boolean result =  "PONG".equals(s);
			pool.returnResource(jedis);
			return result;
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			return false;
		}
	}

	@Override
	public void deleteAll(String pattern) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			Set<String> keys = jedis.keys(pattern);
			if (!keys.isEmpty()) {
				jedis.del(keys.toArray(new String[keys.size()]));
			}
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e + " del pattern:" + pattern);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void set(String key, String value) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			jedis.set(key, value);
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}

	@Override
	public String get(String key) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			String result = jedis.get(key);
			pool.returnResource(jedis);
			return result;
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}

	@Override
	public void delete(String key) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			jedis.del(key);
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}

	@Override
	public void set(String key, List<String> list) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			jedis.del(key);
			for(String val : list){
				jedis.rpush(key, val);
			}
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}

	@Override
	public void incr(String key) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			jedis.incr(key);
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}

	@Override
	public Set<String> getKeysByPattern(String pattern) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			Set<String> keys = jedis.keys(pattern);
			pool.returnResource(jedis);
			return keys;
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}
	
	public void batchIncr(List<String> keys)throws Exception{
		Jedis jedis = pool.getResource();
		try{
			Pipeline p = jedis.pipelined();
			for(String key : keys){
				jedis.incr(key);
			}
			p.execute();
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}

	@Override
	public void batchIncrBy(Map<String,Integer> map)
			throws Exception {
		Jedis jedis = pool.getResource();
		try{
			Pipeline p = jedis.pipelined();
			for(Map.Entry<String, Integer> entry : map.entrySet()){
				jedis.incrBy(entry.getKey(), entry.getValue());
			}
			p.execute();
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
		
	}

	@Override
	public List<String> lrange(String key, int start, int end)throws Exception {
		Jedis jedis = pool.getResource();
		try{
			List<String> list = jedis.lrange(key, start, end);
			pool.returnResource(jedis);
			return list;
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}

	@Override
	public long llen(String key) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			long result = jedis.llen(key);
			pool.returnResource(jedis);
			return result;
		}catch(Exception e){
			pool.returnBrokenResource(jedis);
			LOG.error("redis error: " + e);
			throw e;
		}
	}

	@Override
	public List<String> mGet(List<String> keys) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			List<String> result = jedis.mget(keys.toArray(new String[keys.size()]));
			pool.returnResource(jedis);
			return result;
		}catch(Exception e){
			pool.returnBrokenResource(jedis);
			LOG.error("redis error: " + e);
			throw e;
		}
	}

	@Override
	public void delete(Collection<String> keys) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			jedis.del(keys.toArray(new String[keys.size()]));
			pool.returnResource(jedis);
		}catch(Exception e){
			pool.returnBrokenResource(jedis);
			LOG.error("redis error: " + e);
			throw e;
		}
	}

	@Override
	public void rpush(String key, String value) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			jedis.rpush(key, value);
			pool.returnResource(jedis);
		}catch(Exception e){
			pool.returnBrokenResource(jedis);
			LOG.error("redis error: " + e);
			throw e;
		}
		
	}

	@Override
	public void decr(String key, int decr) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			jedis.decrBy(key, decr);
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}
	
	
	@Override
	public void zAdd(String key, double score, String member) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			jedis.zadd(key, score, member);
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}
	

	@Override
	public Set<String> zRange(String key, int start, int end) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			Set<String> rankSet = jedis.zrange(key, start, end);
			pool.returnResource(jedis);
			return rankSet;
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
		
	}

	@Override
	public long zRank(String key, String member) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			Long rank = jedis.zrank(key, member);
			if(rank==null){
				rank =-1l;
			}
			pool.returnResource(jedis);
			return rank;
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
		
	}

	@Override
	public long zCard(String key) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			long count = jedis.zcard(key);
			pool.returnResource(jedis);
			return count;
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
		
	}
	
	@Override
	public double zScore(String key, String member) throws Exception {
		Jedis jedis = pool.getResource();
		try{
			Double score = jedis.zscore(key, member);
			if(score==null){
				score=0.0;
			}
			pool.returnResource(jedis);
			return score;
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
		
	}
	@Override
	public Set<Tuple> zrangeWithScores(final String key, final int start,final int end) throws Exception {
		 Jedis jedis = pool.getResource();
		try{
			Set<Tuple> set = jedis.zrangeWithScores(key, start, end);
			pool.returnResource(jedis);
			return set;
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}
	 

	@Override
	public void zRemRangeByRank(String key, int start, int end) throws Exception{
		Jedis jedis = pool.getResource();
		try{
			jedis.zremrangeByRank(key, start, end);
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}
	@Override
	public void zRem(String key, String member) throws Exception{
		Jedis jedis = pool.getResource();
		try{
			jedis.zrem(key, member);
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}
	

	@Override
	public void zRemRangeByScore(String key, double min, double max)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void zunionstore(final String dstkey, final ZParams params,final String... sets) throws Exception{
		Jedis jedis = pool.getResource();
		try{
			jedis.zunionstore(dstkey, params, sets);
			pool.returnResource(jedis);
		}catch(Exception e){
			LOG.error("redis error: " + e);
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}
	@Override
	public String rename(String oldkey,String newkey) throws Exception{
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.rename(oldkey, newkey);
		} catch (Exception e) {
			LOG.error("redis error: " + e);
			throw e;
		}finally{
			if(null != jedis){
				pool.returnBrokenResource(jedis);
			}
		}
	}
	@Override
	public Set<String> sMembers(String key) throws Exception{
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.smembers(key);
		} catch (Exception e) {
			LOG.error("redis error: " + e);
			throw e;
		}finally{
			if(null != jedis){
				pool.returnBrokenResource(jedis);
			}
		}
	}
	@Override
	public Long sAdd(String key,String member) throws Exception{
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.sadd(key, member);
		} catch (Exception e) {
			LOG.error("redis error: " + e);
			throw e;
		}finally{
			if(null != jedis){
				pool.returnBrokenResource(jedis);
			}
		}
	}

	@Override
	public boolean exists(String key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			LOG.error("redis error: " + e);
			throw e;
		}finally{
			if(null != jedis){
				pool.returnBrokenResource(jedis);
			}
		}
	}
	
	@Override
	public Set<String> keys(String pattern) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.keys(pattern);
		} catch (Exception e) {
			LOG.error("redis error: " + e);
			throw e;
		}finally{
			if(null != jedis){
				pool.returnBrokenResource(jedis);
			}
		}
	}
	@Override
	public Map<String,String> hgetAll(String key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			LOG.error("redis error: " + e);
			throw e;
		}finally{
			if(null != jedis){
				pool.returnBrokenResource(jedis);
			}
		}
	}
	
}

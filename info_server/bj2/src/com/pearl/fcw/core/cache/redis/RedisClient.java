package com.pearl.fcw.core.cache.redis;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.util.Pool;
import redis.clients.util.SafeEncoder;

import com.pearl.fcw.core.cache.Serializer;

public class RedisClient {

	private static final byte[] BINX = SafeEncoder.encode("NX");
	private static final byte[] BIPX = SafeEncoder.encode("PX");

	private static final String KEYWORD_OK = "OK";

	private final Pool<Jedis> pool;
	private final Serializer serializer;

	public RedisClient(Pool<Jedis> pool, Serializer serializer) {
		this.pool = pool;
		this.serializer = serializer;
	}

	public <R> R execute(Function<Jedis, R> function) {
		try (Jedis jedis = pool.getResource()) {
			return function.apply(jedis);
		}
	}

	public void consume(Consumer<Jedis> comsumer) {
		try (Jedis jedis = pool.getResource()) {
			comsumer.accept(jedis);
		}
	}

	public <T> T get(String key) {
		return execute(jedis -> {
			byte[] v = jedis.get(SafeEncoder.encode(key));
			return serializer.deserialize(v);
		});
	}

	public void set(String key, Object value) {
		consume(jedis -> jedis.set(SafeEncoder.encode(key), serializer.serialize(value)));
	}

	/**
	 * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。
	 * 如果 key 已经存在， SETEX 命令将覆写旧值。
	 * @param key Key
	 * @param expire 生存时间(以秒为单位)
	 * @param value 值
	 */
	public void setex(String key, int expire, Object value) {
		consume(jedis -> jedis.setex(SafeEncoder.encode(key), expire, serializer.serialize(value)));
	}

	public Long del(String key) {
		return execute(jedis -> jedis.del(SafeEncoder.encode(key)));
	}

	public Long del(byte[] key) {
		return execute(jedis -> jedis.del(key));
	}

	/**
	 * 将值 value 关联到 key ，并将 key 的生存时间设为 expireMillis (以毫秒为单位)。
	 * 如果 key 已经存在， 返回结果为False。
	 * @param key
	 * @param value
	 * @param expireMillis 生存时间(以毫秒为单位)
	 * @return
	 */
	public boolean setIfAbsentMillisecond(String key, Object value, long expireMillis) {
		byte[] k = SafeEncoder.encode(key);
		byte[] v = serializer.serialize(value);
		String r = execute(jedis -> jedis.set(k, v, BINX, BIPX, expireMillis));
		return KEYWORD_OK.equals(r);
	}

	/**
	 * @see redis.clients.jedis.BinaryJedis#zadd(byte[], double, byte[])
	 */
	public Long zadd(String key, double score, Object member) {
		byte[] k = SafeEncoder.encode(key);
		byte[] m = serializer.serialize(member);
		return execute(jedis -> jedis.zadd(k, score, m));
	}

	/**
	 * @see redis.clients.jedis.BinaryJedis#zadd(byte[], double, byte[])
	 */
	public Long zAdd(String key, double score, Object member) {
		return zadd(key, score, member);
	}

	/**
	 * @see redis.clients.jedis.BinaryJedis#zrevrange(byte[], long, long)
	 */
	public <T> Set<T> zrevrange(String key, long start, long end) {
		byte[] k = SafeEncoder.encode(key);
		Set<byte[]> r = execute(jedis -> jedis.zrevrange(k, start, end));
		return r.stream().<T> map(serializer::deserialize).collect(Collectors.toSet());
	}

	/**
	 * @see redis.clients.jedis.BinaryJedis#zrevrank(byte[], byte[])
	 */
	public Long zrevrank(String key, Object member) {
		byte[] k = SafeEncoder.encode(key);
		byte[] m = serializer.serialize(member);
		return execute(jedis -> jedis.zrevrank(k, m));
	}

	public boolean exists(String key) {
		byte[] k = SafeEncoder.encode(key);
		return execute(jedis -> jedis.exists(k));
	}

	/**
	 * 将信息发送到指定的频道。
	 * @param channel 频道
	 * @param message 信息
	 * @return 接收到信息的订阅者数量
	 */
	public Long publish(String channel, Object message) {
		byte[] c = SafeEncoder.encode(channel);
		byte[] m = serializer.serialize(message);
		return execute(jedis -> jedis.publish(c, m));
	}

	public List<Object> hmget(String key, String... fields) {
		byte[] k = SafeEncoder.encode(key);
		byte[][] f = SafeEncoder.encodeMany(fields);
		return execute(jedis -> jedis.hmget(k, f)).stream().map(serializer::deserialize).collect(Collectors.toList());
	}

	public <T> T hget(String key, String field) {
		byte[] k = SafeEncoder.encode(key);
		byte[] f = SafeEncoder.encode(field);
		byte[] v = execute(jedis -> jedis.hget(k, f));
		return serializer.deserialize(v);
	}

	public void hset(String key, String field, Object value) {
		byte[] k = SafeEncoder.encode(key);
		byte[] f = SafeEncoder.encode(field);
		byte[] v = serializer.serialize(value);
		consume(jedis -> jedis.hset(k, f, v));
	}

	public void hsetnx(String key, String field, Object value) {
		byte[] k = SafeEncoder.encode(key);
		byte[] f = SafeEncoder.encode(field);
		byte[] v = serializer.serialize(value);
		consume(jedis -> jedis.hsetnx(k, f, v));
	}

	/**
	 * 该key总数
	 * @param key
	 * @return
	 */
	public long zCard(String key) {
		byte[] k = SafeEncoder.encode(key);
		return execute(jedis -> jedis.zcard(k));
	}

	/**
	 * 该key的在start跟end的数值
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<Tuple> zrangeWithScores(String key, int start, int end) {
		byte[] k = SafeEncoder.encode(key);
		return execute(jedis -> jedis.zrangeWithScores(k, start, end));
	}

	/**
	 * @param rKey
	 * @param valueOf
	 * @return
	 */
	public long zRank(String key, String valueOf) {
		byte[] k = SafeEncoder.encode(key);
		byte[] v = SafeEncoder.encode(valueOf);
		return execute(jedis -> jedis.zrank(k, v));
	}

	public double zScore(String key, String member) {
		byte[] k = SafeEncoder.encode(key);
		byte[] m = SafeEncoder.encode(member);
		return execute(jedis -> jedis.zscore(k, m));
	}

	public void zRem(String key, String member) {
		byte[] k = SafeEncoder.encode(key);
		byte[] v = SafeEncoder.encode(member);
		consume(jedis -> jedis.zrem(k, v));
	}

	public Set<String> zRange(String key, int start, int end) {
		return execute(jedis -> jedis.zrange(key, start, end));
	}

	public long zCount(String key, double min, double max) {
		return execute(jedis -> jedis.zcount(key, min, max));
	}

	public static void main(String[] args) throws Exception {
		Jedis jedis = new Jedis("127.0.0.1", 6380);
		jedis.zadd("z", 10, "p1");
		jedis.zadd("z", 11, "p2");
		jedis.zadd("z", 9, "p3");
		jedis.zadd("z", 45, "p4");
		jedis.zadd("z", 19, "p5");
		jedis.zadd("z", 2, "p6");
		jedis.zadd("z", 5, "p7");
		jedis.zadd("z", 22, "p8");
		jedis.zadd("z", 11, "p9");
		jedis.zadd("z", 15, "p10");
		System.out.println(jedis.zcard("z"));
		System.out.println(jedis.zscore("z", "p2"));
		System.out.println(jedis.zcount("z", 10, 11));
		System.out.println(jedis.zrange("z", 0, 5));
		System.out.println(jedis.zrank("z", "p1"));

		System.out.println(Math.round(0.5));

		//		String b = "";
		//		b = jedis.set("testKey1", "testValue1", "nx", "px", 5000);
		//		System.out.println(b);
		//		b = jedis.set("testKey2", "testValue2", "nx", "px", 5000);
		//		System.out.println(b);

		//        b = jedis.setex("testKey2", 5, "testValue2");
		//        System.out.println(b);
		//        System.out.println(jedis.get("testKey2"));
		//        b = jedis.setex("testKey2", 5, "testValue22");
		//        System.out.println(b);
		//        System.out.println(jedis.get("testKey2"));
		//        Thread.sleep(4000);
		//        System.out.println(jedis.get("testKey2"));

		jedis.close();
	}
}

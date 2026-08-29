package com.pearl.fcw;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.pearl.fcw.core.cache.CacheKey;
import com.pearl.fcw.core.cache.EntityManager;
import com.pearl.fcw.core.cache.KryoFactory;
import com.pearl.fcw.core.cache.KryoSerializer;
import com.pearl.fcw.core.cache.OperationCache;
import com.pearl.fcw.core.cache.TransactionManager;
import com.pearl.fcw.core.cache.redis.RedisClient;
import com.pearl.fcw.lobby.pojo.json.JsonItemDuration;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.utils.GameThreadPool;
import com.pearl.fcw.utils.JsonUtil;
import com.pearl.fcw.utils.LobbyCacheKey;
import com.pearl.o2o.utils.ConfigurationUtil;

@Configuration
@ComponentScan
public class AppConfig {
	public AppConfig() {
	}

	/**
	 * 无法找到Tomcat启动容器后触发AppConfig内Bean实例的destroy-method的办法，所以手动加关闭钩子
	 * @return
	 */
	@Bean
	public Object appShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				operationCache().close();
				cacheManager().shutdown();
			}
		});
		return null;
	}

	@Bean
	public CacheManager cacheManager() {
		//因使用手动钩子，所以将该句注释
		//        System.setProperty("net.sf.ehcache.enableShutdownHook", "true");
		CacheManager cacheManager = CacheManager.create(getClass().getResource("/config/ehcache.xml").getPath());
		return cacheManager;
	}

	@Bean
	public Ehcache localCache() {
		Cache localCache = cacheManager().getCache("bj2");
		return localCache;
	}

	@Bean
	public CacheKey cacheKey() {
		return new CacheKey();
	}

	@Bean
	public LobbyCacheKey lobbyCacheKey() {
		return new LobbyCacheKey();
	}

	@Bean
	public EntityManager entityManager() {
		return new EntityManager();
	}

	/**
	 * 第三方序列化和反序列化工具
	 * @return
	 */
	@Bean
	public KryoSerializer kryoSerializer() {
		KryoFactory kryoFactory = new KryoFactory();
		return new KryoSerializer(kryoFactory);
	}

	@Bean
	public RedisClient redisClient() {
		JedisPoolConfig jc = new JedisPoolConfig();
		jc.setMinIdle(1);
		return new RedisClient(new JedisPool(jc, ConfigurationUtil.REDIS_HOST, ConfigurationUtil.REDIS_PORT), kryoSerializer());
	}

	@Bean
	public TransactionManager transactionManager() {
		return new TransactionManager();
	}

	@Bean
	public OperationCache operationCache() {
		//        return new OperationCache(cacheKey(), localCache(), redisClient(), ConfigurationUtil.EHCACHE_DATABASE_FLUSH_SECONDS, ConfigurationUtil.EHCACHE_DATABASE_FLUSH_INTERVAL_SECONDS);
		return new OperationCache(cacheKey(), localCache(), redisClient(), 30, 60);
	}

	@Bean
	public ProtoConverter protoConverter() {
		return new ProtoConverter();
	}

	@Bean
	public GameThreadPool gameThreadPool() {
		return new GameThreadPool();
	}

	public static void main(String[] args) throws Exception {
		JsonItemDuration i = new JsonItemDuration();
		String str = JsonUtil.writeAsString(i);
		System.out.println(str);
		//		System.out.println(CommonMsg.GUN_PROPERTY_MSG[44]);
	}
}

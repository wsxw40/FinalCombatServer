/**
 * 
 */
package com.pde.uweb.framework.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.log4j.Logger;

/**
 * @author Huanggang
 * 
 */
public class HttpSessionService {

	private static final Logger logger = Logger.getLogger(HttpSessionService.class);

	private static final HttpSessionService instance = new HttpSessionService();
	
	private static final int sessionTime=1000*60*30;

	private MemcachedClient memcachedClient;

	private HttpSessionService() {
		this.makeMemcacheConnection();
	}

	public static  HttpSessionService getInstance() {
	
		return instance;

	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getSession(String sessionId) {
	 
		Map<String, Object> session = null;

		try {
			session = ((Map<String, Object>) this.memcachedClient.get(sessionId));
			if (session == null) {
				session = new HashMap<String, Object>();
			}
			 this.memcachedClient.set(sessionId, HttpSessionService.sessionTime, session);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			logger.error(e);
		}
		return session;

	}

	
	public void saveSession(String id, String key, Object arg1) {
		final String k = key;
		final Object o = arg1;
		try {
			this.memcachedClient.cas(id, HttpSessionService.sessionTime, new CASOperation<Map<String, Object>>() {
				// 尝试更新5次
				public int getMaxTries() {
					return 5;
				}

				public Map<String, Object> getNewValue(long currentCAS, Map<String, Object> map) {
					map.put(k, o);
					return map;
				}
			});
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			logger.error(e);
		}

	}

 
	public void removeAttribute(String sessionId, String key) {
		final String k = key;
		try {
			this.memcachedClient.cas(sessionId,HttpSessionService.sessionTime, new CASOperation<Map<String, Object>>() {
				// 尝试更新5次
				public int getMaxTries() {
					return 5;
				}
				public Map<String, Object> getNewValue(long currentCAS, Map<String, Object> map) {
					map.remove(k);
					return map;
				}
			});
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			logger.error(e);
		}

	}

	public void removeSession(String sessionId) {
	 
			try {
				this.memcachedClient.delete(sessionId);
			} catch (TimeoutException | InterruptedException | MemcachedException e) {
				logger.error(e);
			}
		 
	 
	}

	/**
	 * @return the memcachedClient
	 */
	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	private void makeMemcacheConnection() {
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File("/opt/config/UWEBGlobal.properties"));
			properties.load(inputStream);
			String memcachedUrl = properties.getProperty("http.session.memcached.url");
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(memcachedUrl));
			this.memcachedClient = builder.build();
			// 超时时间10秒
			this.memcachedClient.setOpTimeout(10 * 1000L);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}

		}

	}


}

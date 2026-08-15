/**
 * 
 */
package com.pde.uweb.cache.service.impl;

import com.pde.infor.common.base.cache.Cache;
import com.pde.infor.common.base.cache.CacheManager;
import com.pde.uweb.cache.service.StaticResourceService;
import com.pde.uweb.database.ma.security.QuestionPojo;
import com.pde.uweb.database.ws.channel.ChannelPojo;
import com.pde.uweb.framework.asserts.UWEBAssert;
import com.pde.uweb.framework.constants.UWEBConstant;
import com.pde.uweb.ma.service.MemberService;

import java.util.Map;

/**
 * 静态资源服务实现类
 * 
 * @author Huanggang
 */
public class StaticResourceServiceImpl implements StaticResourceService {

	private CacheManager cacheManager;
	private MemberService memberService;
	
	/** 本地缓存对象 */
	private Cache<Object, Object> localCache;
	
	/**
	 * 获取db中最新数据，初始化 cache
	 * 启动application时，自动加载的方法
	 */
	public void initLoadData() {
		// 创建 local cache
		// 总的cache是localResources，下面再有channelResource, userResource等
		localCache = this.cacheManager.getCache(UWEBConstant.LOCAL_CACHE_NAME);
		if (localCache!=null) {
			
			// 加载 channel cache

			// 加载 question cache
			Map<String, QuestionPojo> questionCache = memberService.getAllQuestionsMap();
			this.createStaticResource(UWEBConstant.QUESTION_CACHE_NAME, questionCache);
		}
	}
	
	/**
	 * 获得指定的cache
	 * 
	 * @param key cache_name
	 * @return 返回cache的值，若无该cache，返回null
	 */
	@Override
	public Object getStaticResource(String key) {
		if (UWEBAssert.assertStringIsNull(key))
			return null;
		
		return localCache.get(key);
	}
	
	/** 新增 cache */
	public void createStaticResource(String key, Object value) {
		// 若key不为空，就增加这个cache
		if (!UWEBAssert.assertStringIsNull(key))
			localCache.put(key, value);
	}
	
	/**
	 * 追加或修改 channel cache的内容
	 * 
	 * @param channel 待更新的 channel
	 */
	@Override
	public void modifyChannelStaticResource(ChannelPojo channel) {
		if (channel==null)
			return;
		
		Object obj = this.getStaticResource(UWEBConstant.CHANNEL_CACHE_NAME);
		if (obj != null) {
			Map<String, ChannelPojo> map = (Map<String, ChannelPojo>)obj;
			map.put(String.valueOf(channel.getChannelId()), channel);
		}
	}
	
	/** 
	 * 删除 channel cache 的内容
	 * 
	 * @param channel_id 即map的key
	 */
	@Override
	public void removeChannelStaticResource(String channel_id) {
		if (UWEBAssert.assertStringIsNull(channel_id))
			return;

		// 获得 channel map
		Object obj = this.getStaticResource(UWEBConstant.CHANNEL_CACHE_NAME);
		if (obj!=null) {
			Map<String, ChannelPojo> map = (Map<String, ChannelPojo>)obj;
			map.remove(channel_id);
		}
	}
	

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

}

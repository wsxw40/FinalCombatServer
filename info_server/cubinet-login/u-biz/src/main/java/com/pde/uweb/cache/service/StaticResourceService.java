/**
 * 
 */
package com.pde.uweb.cache.service;

import com.pde.uweb.database.ws.channel.ChannelPojo;

/**
 * 静态资源服务接口（加载db数据到缓存中等）
 * 
 * @author Huanggang
 */
public interface StaticResourceService {

	/**
	 * 获得指定的cache
	 * 
	 * @param key cache_name
	 * @return 返回cache的值，若无该cache，返回null
	 */
	public Object getStaticResource(String key);
	
	/**
	 * 追加或修改 channel cache的内容
	 */
	public void modifyChannelStaticResource(ChannelPojo channel);
	
	/** 
	 * 删除 channel cache 的内容
	 * 
	 * @param channel_id 即map的key
	 */
	public void removeChannelStaticResource(String channel_id);
}

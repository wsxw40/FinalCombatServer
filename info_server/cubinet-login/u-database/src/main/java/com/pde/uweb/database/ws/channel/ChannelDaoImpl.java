package com.pde.uweb.database.ws.channel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title 		自定义dao方法接口的默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: ChannelDaoImpl,v 1.0 2012-09-06 Sean Weng Exp $
 * @create		2012-09-06
 */
public class ChannelDaoImpl extends AbstractChannelDao {
	
	/**
	 * 更新渠道状态
	 *
	 * @return 更新是否成功
	 */
	@Override
	public boolean updateChannelStatus(ChannelPojo channel, int status) {
		if (channel == null)
			return false;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", channel.getChannelId());
		map.put("version", channel.getVersion());
		map.put("status", status);
		return this.getSqlMapClientTemplate().update("Channel.updateStatus", map) == Integer.valueOf(1);
	}

	/**
	 *  查得所有的channel记录
	 *  游戏区服是未关闭状态
	 */
	@Override
	public List<ChannelPojo> getAllOpenChannel() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", ChannelPojo.SERVICE_STATUS_OPEN);
		
		Object result = this.getSqlMapClientTemplate().queryForList("Channel.getAllOpenChannel", map);
		return (result != null) ? (List<ChannelPojo>)result : null;
	}

    @Override
	public List<ChannelPojo> getAllChannel() {
		return this.getSqlMapClientTemplate().queryForList("Channel.getAllChannel");
	}

    @Override
    public Map<Long,ChannelPojo> getAllOpenChannelMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", ChannelPojo.SERVICE_STATUS_OPEN);

        return this.getSqlMapClientTemplate().queryForMap("Channel.getAllOpenChannel", map,"channelId");
    }
	/**
	 * 根参数map定位到一条channel记录
	 * 
	 * @param paramMap 参数map
	 * @return 返回查询结果，找不到记录返回null
	 */
	@Override
	public ChannelPojo getChannelByParam(Map<String, Object> paramMap) {
		if (paramMap==null || paramMap.isEmpty()) {
			return null;
		}
		Object result = this.getSqlMapClientTemplate().queryForObject("Channel.getByParam", paramMap);
		return (result != null) ? (ChannelPojo)result : null;
	}

}

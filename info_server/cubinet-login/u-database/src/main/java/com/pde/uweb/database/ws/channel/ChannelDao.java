package com.pde.uweb.database.ws.channel;

import java.util.List;
import java.util.Map;

import com.pde.infor.common.dao.EntityDao;

/**
 * @title 自定义dao方法接口
 * @description
 * @usage
 * @copyright Copyright 2012 PDE Corporation. All rights reserved.
 * @company PDE Corporation.
 * @author Sean Weng <wengxiaofan@pde.com>
 * @version $Id: ChannelDao,v 1.0 2012-09-06 Sean Weng Exp $
 * @create 2012-09-06
 */
public interface ChannelDao extends EntityDao<ChannelPojo> {

	/**
	 * 更新渠道状态
	 * 
	 * @param channel 待更新的channel对象
	 * @param status 渠道状态
	 * @return 更新是否成功
	 */
	public boolean updateChannelStatus(ChannelPojo channel, int status);

	/**
	 * 根参数map定位到一条channel记录
	 * 
	 * @param paramMap 参数map  <br>
	 * 
	 * 需要以下5个参数，才能定位到一条唯一的channel记录  <br>
	 * gameService 游戏区服  <br>
	 * partnerId 合作商id  <br>
	 * proxyId 代理ip  <br>
	 * proxyPort 代理端口  <br>
	 * gameNumber 游戏编号
	 * 
	 * @return 返回查询结果，找不到记录返回null
	 */
	public ChannelPojo getChannelByParam(Map<String, Object> paramMap);
	
	/**
	 *  查得所有的channel记录 （游戏区服是未关闭状态）
	 */
	public List<ChannelPojo> getAllOpenChannel();

    Map<Long,ChannelPojo> getAllOpenChannelMap();

    List<ChannelPojo> getAllChannel();
}
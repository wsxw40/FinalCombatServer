package com.pde.uweb.database.ws.channel;

/**
 * @title 		 ChannelDao访问接口默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: ChannelDao,v 1.0 2012-09-06 Sean Weng Exp $
 * @create		2012-09-06
 */
public class ChannelPojo extends AbstractChannelPojo {
	
	/**
	 * 游戏区服状态--正常
	 */
	public static final int SERVICE_STATUS_OPEN = Integer.valueOf(1);
	
	/**
	 * 游戏区服状态--关闭
	 */
	public static final int SERVICE_STATUS_CLOSE = Integer.valueOf(0);
	
	
	
}
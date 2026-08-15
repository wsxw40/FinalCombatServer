package com.pde.uweb.database.cdkey.gamecdkeytype;

/**
 * @title 		 GameCdKeyTypeDao访问接口默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		lifengyang <lifengyang@pde.com>
 * @version		$Id: GameCdKeyTypeDao,v 1.0 2012-09-26 lifengyang Exp $
 * @create		2012-09-26
 */
public class GameCdKeyTypePojo extends AbstractGameCdKeyTypePojo {

	// 常量集
    /**
     * 状态--正常
     */
    public static final int SERVICE_STATUS_ENABLE = 1;

    /**
     * 状态--关闭
     */
    public static final int SERVICE_STATUS_DISABLE = 0;
	/**
	 * 例：激活
	 */
	// public static final int statusActive = Integer.valueOf(1);
}
package com.pde.uweb.database.cdkey.gamecdkey;

import com.pde.infor.common.dao.EntityDao;

import java.util.List;

/**
 * @title 		自定义dao方法接口
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		lifengyang <lifengyang@pde.com>
 * @version		$Id: GameCdKeyDao,v 1.0 2012-09-26 lifengyang Exp $
 * @create		2012-09-26
 */
public interface GameCdKeyDao extends EntityDao<GameCdKeyPojo> {

	// 自定义dao方法

    List<GameCdKeyPojo> selectAllOneUser(long userId);

    GameCdKeyPojo selectByCdkey(String cdkey);

    List<GameCdKeyPojo> selectByChannel(long channelId);

    List<GameCdKeyPojo> selectByChannelAndGameCdKeyType(long channelId, long gameCdKeyTypeId);
}
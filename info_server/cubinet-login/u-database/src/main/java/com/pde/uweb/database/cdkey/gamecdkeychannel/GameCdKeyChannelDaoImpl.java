package com.pde.uweb.database.cdkey.gamecdkeychannel;

import java.util.List;
import java.util.Map;

/**
 * @title 		自定义dao方法接口的默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		lifengyang <lifengyang@pde.com>
 * @version		$Id: GameCdKeyChannelDaoImpl,v 1.0 2012-09-26 lifengyang Exp $
 * @create		2012-09-26
 */
public class GameCdKeyChannelDaoImpl extends AbstractGameCdKeyChannelDao {
    @Override
    public Map<Long,GameCdKeyChannelPojo> selectAll() {
        return this.getSqlMapClientTemplate().queryForMap("GameCdKeyChannel.selectAll", null, "id");
    }

    @Override
    public Map<Long,GameCdKeyChannelPojo> selectAllEnable() {
        return this.getSqlMapClientTemplate().queryForMap("GameCdKeyChannel.selectAllEnable", null, "id");
    }

    @Override
    public GameCdKeyChannelPojo selectByChannel(long channelId) {
        return (GameCdKeyChannelPojo)this.getSqlMapClientTemplate().queryForObject("GameCdKeyChannel.selectByChannel", channelId);
    }
}
package com.pde.uweb.database.cdkey.gamecdkey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title 		自定义dao方法接口的默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		lifengyang <lifengyang@pde.com>
 * @version		$Id: GameCdKeyDaoImpl,v 1.0 2012-09-26 lifengyang Exp $
 * @create		2012-09-26
 */
public class GameCdKeyDaoImpl extends AbstractGameCdKeyDao {
    @Override
    public List<GameCdKeyPojo> selectAllOneUser(long userId) {
        return this.getSqlMapClientTemplate().queryForList("GameCdKey.selectAllOneUser", userId);
    }

    @Override
    public GameCdKeyPojo selectByCdkey(String cdkey) {
        return (GameCdKeyPojo) this.getSqlMapClientTemplate().queryForObject("GameCdKey.selectByCdkey", cdkey);
    }

    @Override
    public List<GameCdKeyPojo> selectByChannel(long channelId) {
        return this.getSqlMapClientTemplate().queryForList("GameCdKey.selectByChannel", channelId);
    }

    @Override
    public List<GameCdKeyPojo> selectByChannelAndGameCdKeyType(long channelId,long gameCdKeyTypeId) {
        Map<String,Long> param = new HashMap<>(2,1);
        param.put("channelId",channelId);
        param.put("gameCdKeyTypeId",gameCdKeyTypeId);
        return this.getSqlMapClientTemplate().queryForList("GameCdKey.selectByChannelAndGameCdKeyType", param);
    }
}
package com.pde.uweb.database.cdkey.gamecdkeytype;

import java.util.Map;

/**
 * @title 		自定义dao方法接口的默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		lifengyang <lifengyang@pde.com>
 * @version		$Id: GameCdKeyTypeDaoImpl,v 1.0 2012-09-26 lifengyang Exp $
 * @create		2012-09-26
 */
public class GameCdKeyTypeDaoImpl extends AbstractGameCdKeyTypeDao {
    @Override
    public Map<Long, GameCdKeyTypePojo> selectAll() {
        return this.getSqlMapClientTemplate().queryForMap("GameCdKeyType.selectAll", null, "id");
    }

    @Override
    public Map<Long,GameCdKeyTypePojo> selectAllEnable() {
        return this.getSqlMapClientTemplate().queryForMap("GameCdKeyType.selectAllEnable",null,"id");
    }
}
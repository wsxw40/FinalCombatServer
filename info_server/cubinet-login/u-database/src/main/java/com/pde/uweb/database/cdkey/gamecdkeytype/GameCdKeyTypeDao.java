package com.pde.uweb.database.cdkey.gamecdkeytype;

import com.pde.infor.common.dao.EntityDao;

import java.util.List;
import java.util.Map;

/**
 * @title 		自定义dao方法接口
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		lifengyang <lifengyang@pde.com>
 * @version		$Id: GameCdKeyTypeDao,v 1.0 2012-09-26 lifengyang Exp $
 * @create		2012-09-26
 */
public interface GameCdKeyTypeDao extends EntityDao<GameCdKeyTypePojo> {

	// 自定义dao方法

    Map<Long, GameCdKeyTypePojo> selectAll();

    Map<Long,GameCdKeyTypePojo> selectAllEnable();
}
package com.pde.uweb.database.partner.partneruser;

import java.util.HashMap;
import java.util.Map;

/**
 * @title 		自定义dao方法接口的默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: PartnerUserDaoImpl,v 1.0 2012-12-13 Sean Weng Exp $
 * @create		2012-12-13
 */
public class PartnerUserDaoImpl extends AbstractPartnerUserDao {
	
	@Override
	public long getUidByGameAccount(String partnerId, String gameAccount) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("partnerId", partnerId);
		map.put("gameAccount", gameAccount);
		Object result = this.getSqlMapClientTemplate().queryForObject("PartnerUser.selectUidByGameAccount", map);		
		return (result != null) ? (Long)result : Long.valueOf(0);
	}
	
	@Override
	public long getUidByEm(String partnerId, String em) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("partnerId", partnerId);
		map.put("email", em);
		Object result = this.getSqlMapClientTemplate().queryForObject("PartnerUser.selectUidByEm", map);		
		return (result != null) ? (Long)result : Long.valueOf(0);
	}

}

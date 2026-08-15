package com.pde.uweb.database.partner.partner;

import java.util.HashMap;
import java.util.Map;

/**
 * @title 		自定义dao方法接口的默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: PartnerDaoImpl,v 1.0 2012-12-13 Sean Weng Exp $
 * @create		2012-12-13
 */
public class PartnerDaoImpl extends AbstractPartnerDao {

	@Override
	public long getPartnerPkId(String partnerId, int status) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("partnerId", partnerId);
		map.put("status", String.valueOf(status));
		Object result = this.getSqlMapClientTemplate().queryForObject("Partner.getPartnerPkId", map);
		return (result != null) ? (Long)result : Long.valueOf(0);
	}
	
}
package com.pde.uweb.database.partner.partneruser;

import com.pde.infor.common.dao.EntityDao;

/**
 * @title 		自定义dao方法接口
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: PartnerUserDao,v 1.0 2012-12-13 Sean Weng Exp $
 * @create		2012-12-13
 */
public interface PartnerUserDao extends EntityDao<PartnerUserPojo> {

	/**
	 *  根据参数获得 user_id
	 *  
	 *  @param partnerId 合作商id
	 *  @param gameAccount 游戏账号
	 *  @return 返回 user_id
	 */
	public long getUidByGameAccount(String partnerId, String gameAccount);
	
	/**
	 *  根据参数获得 user_id
	 *  
	 *  @param partnerId 合作商id
	 *  @param em email
	 *  @return 返回 user_id
	 */
	public long getUidByEm(String partnerId, String em);

}
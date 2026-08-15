package com.pde.uweb.database.partner.partneruser;

/**
 * @title 		 PartnerUserPojo 对象
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: PartnerUserDao,v 1.0 2012-12-13 Sean Weng Exp $
 * @create		2012-12-13
 */
public class PartnerUserPojo extends AbstractPartnerUserPojo {

	/** 有效 */
	public static final int STATUS_ACTIVE = Integer.valueOf(1);
	
	/** 无效 */
	public static final int STATUS_INACTIVE = Integer.valueOf(0);
}
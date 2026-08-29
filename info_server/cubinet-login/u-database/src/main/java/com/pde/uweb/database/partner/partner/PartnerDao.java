package com.pde.uweb.database.partner.partner;

import com.pde.infor.common.dao.EntityDao;

/**
 * @title 		自定义dao方法接口
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: PartnerDao,v 1.0 2012-12-13 Sean Weng Exp $
 * @create		2012-12-13
 */
public interface PartnerDao extends EntityDao<PartnerPojo> {

	/** 
	 *  根据指定参数获得合作商记录的主键值
	 * 
	 *  @param partnerId 合作商id
	 *  @param status 记录状态
	 */
	public long getPartnerPkId(String partnerId, int status);

}
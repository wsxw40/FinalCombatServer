package com.pde.uweb.database.cms.cmsnewscolumn;

import java.util.List;

import com.pde.infor.common.dao.EntityDao;

/**
 * @title 		自定义dao方法接口
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: CmsNewsColumnDao,v 1.0 2012-09-27 Sean Weng Exp $
 * @create		2012-09-27
 */
public interface CmsNewsColumnDao extends EntityDao<CmsNewsColumnPojo> {

	/**
	 * 获得所有的有效新闻栏目
	 */
	public List<CmsNewsColumnPojo> selectAll();
}
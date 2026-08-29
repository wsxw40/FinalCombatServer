package com.pde.uweb.database.cms.cmsnewscolumn;

import java.util.List;

/**
 * @title 		自定义dao方法接口的默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: CmsNewsColumnDaoImpl,v 1.0 2012-09-27 Sean Weng Exp $
 * @create		2012-09-27
 */
public class CmsNewsColumnDaoImpl extends AbstractCmsNewsColumnDao {
	
	/**
	 * 获得所有的有效新闻栏目
	 */
	public List<CmsNewsColumnPojo> selectAll() {

		Object result = this.getSqlMapClientTemplate().queryForList("CmsNewsColumn.selectAll");
		return (result != null) ? (List<CmsNewsColumnPojo>)result : null;
	}

}
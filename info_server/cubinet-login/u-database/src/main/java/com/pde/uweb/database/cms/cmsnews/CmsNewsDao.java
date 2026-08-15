package com.pde.uweb.database.cms.cmsnews;

import java.util.List;

import com.pde.infor.common.dao.EntityDao;

/**
 * @title 		自定义dao方法接口
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: CmsNewsDao,v 1.0 2012-09-27 Sean Weng Exp $
 * @create		2012-09-27
 */
public interface CmsNewsDao extends EntityDao<CmsNewsPojo> {

	/**
	 * 根据查询条件，获得新闻资讯的总记录数
	 * 
	 * @param filter 查询条件
	 */
	public int getNewsCount(CmsNewsSearchFilter filter);
	
	/**
	 * 根据查询条件，获得新闻资讯信息
	 * 
	 * @param filter 查询条件
	 * @return 新闻资讯对象集合，没有记录返回null
	 */
	public List<CmsNewsPojo> getNewsByFilter(CmsNewsSearchFilter filter);

}
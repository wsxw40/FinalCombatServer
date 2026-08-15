package com.pde.uweb.database.cms.cmsnews;

/**
 * @title 		 CmsNewsDao访问接口默认实现
 * @description
 * @usage
 * @copyright	Copyright 2012 PDE Corporation. All rights reserved.
 * @company		PDE Corporation.
 * @author		Sean Weng <wengxiaofan@pde.com>
 * @version		$Id: CmsNewsDao,v 1.0 2012-09-27 Sean Weng Exp $
 * @create		2012-09-27
 */
public class CmsNewsPojo extends AbstractCmsNewsPojo {

	/**
	 * 新闻状态--激活
	 */
	public static final int STATUS_NEWS_ACTIVE = Integer.valueOf(1);
	
	/**
	 * 新闻状态--未激活
	 */
	public static final int STATUS_NEWS_INACTIVE = Integer.valueOf(0);
	
	
	public String getStrNewId(){
		return this.getNewsId()+"";
	}
	
}
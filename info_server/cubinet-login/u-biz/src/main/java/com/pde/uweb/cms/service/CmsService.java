/**
 * 
 */
package com.pde.uweb.cms.service;

import java.util.List;

import com.pde.uweb.cms.dto.CreateNewsRequestDto;
import com.pde.uweb.cms.dto.ModifyNewsRequestDto;
import com.pde.uweb.database.cms.cmsnews.CmsNewsPojo;
import com.pde.uweb.database.cms.cmsnews.CmsNewsSearchFilter;
import com.pde.uweb.database.cms.cmsnewscolumn.CmsNewsColumnPojo;

/**
 *  信息发布系统服务接口实现
 * 
 *  @author sean.weng
 */
public interface CmsService {

	/**
	 * 创建新闻
	 */
	public boolean doCreateNewsRnTx(CreateNewsRequestDto requestDto) throws Exception;

	/**
	 * 编辑新闻
	 */
	public boolean doModifyNewsRnTx(ModifyNewsRequestDto requestDto) throws Exception;
	
	/**
	 * 获得所有有效的新闻栏目
	 * 
	 * @return Map< newsId, newsInfo >
	 */
	public List<CmsNewsColumnPojo> getNewsColumn();
	
	/**
	 * 根据新闻编号获得新闻资讯信息
	 * 
	 * @param newsId 新闻编号
	 * @return 新闻资讯对象，没有记录返回null
	 */
	public CmsNewsPojo getNewsById(long newsId);
	
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
	
	/**
	 * 根据查询条件，获得新闻资讯信息
	 * 
	 * @param newsId 新闻记录id号
	 * @return 删除是否成功
	 */
	public boolean deleteNewsById(long newsId);

}

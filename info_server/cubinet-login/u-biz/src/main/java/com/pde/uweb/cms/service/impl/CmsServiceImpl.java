/**
 * 
 */
package com.pde.uweb.cms.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.pde.infor.common.utils.KeyUtil;
import com.pde.uweb.cms.dto.CreateNewsRequestDto;
import com.pde.uweb.cms.dto.ModifyNewsRequestDto;
import com.pde.uweb.cms.service.CmsService;
import com.pde.uweb.database.cms.cmsnews.CmsNewsDao;
import com.pde.uweb.database.cms.cmsnews.CmsNewsPojo;
import com.pde.uweb.database.cms.cmsnews.CmsNewsSearchFilter;
import com.pde.uweb.database.cms.cmsnewscolumn.CmsNewsColumnDao;
import com.pde.uweb.database.cms.cmsnewscolumn.CmsNewsColumnPojo;

/**
 * 信息发布系统服务接口实现
 *
 * @author sean.weng
 */
public class CmsServiceImpl implements CmsService {

	private CmsNewsDao newsDao;
	
	private CmsNewsColumnDao newsColumnDao;

	private static final Logger logger = Logger.getLogger(CmsServiceImpl.class);

	/**
	 * 创建新闻
	 */
	public boolean doCreateNewsRnTx(CreateNewsRequestDto requestDto) 
			throws Exception {
		
		CmsNewsPojo cmsNews = new CmsNewsPojo();
		cmsNews.setNewsId(KeyUtil.generateDBKey());
		cmsNews.setHeadline(requestDto.getHeadline());
		cmsNews.setSubhead(requestDto.getSubhead());
		cmsNews.setContent(requestDto.getContent());
		cmsNews.setNewsColumnId(requestDto.getNewsColumnId());
		cmsNews.setStatus(requestDto.getStatus());
		cmsNews.setAuthor(requestDto.getAuthor());
		cmsNews.setType(requestDto.getType());
		cmsNews.setPriorityLevel(requestDto.getPriorityLevel());
		cmsNews.setThumbnails(requestDto.getThumbnails());
		
		try {
			newsDao.add(cmsNews);
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	/**
	 * 编辑新闻
	 */
	public boolean doModifyNewsRnTx(ModifyNewsRequestDto requestDto) 
			throws Exception {

		CmsNewsPojo cmsNews = newsDao.select(requestDto.getNewsId());
		if (cmsNews==null) 
			return false;

		cmsNews.setContent(requestDto.getContent());
		cmsNews.setHeadline(requestDto.getHeadline());
		cmsNews.setSubhead(requestDto.getSubhead());
		cmsNews.setContent(requestDto.getContent());
		cmsNews.setAuthor(requestDto.getAuthor());
		cmsNews.setHeadlineStatus(requestDto.getHeadlineStatus());
		cmsNews.setNewsColumnId(requestDto.getNewsColumnId());
		cmsNews.setPriorityLevel(requestDto.getPriorityLevel());
		cmsNews.setStatus(requestDto.getStatus());
		cmsNews.setTopLevel(requestDto.getTopLevel());
		cmsNews.setType(requestDto.getType());
		cmsNews.setThumbnails(requestDto.getThumbnails());

		try {
			return newsDao.updateForField(cmsNews);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	
	/**
	 * 获得所有有效的新闻栏目
	 */
	public List<CmsNewsColumnPojo> getNewsColumn() {
		return newsColumnDao.selectAll();
	}
	
	/**
	 * 根据新闻编号获得新闻资讯信息
	 * 
	 * @param newsId 新闻编号
	 * @return 新闻资讯对象，没有记录返回null
	 */
	public CmsNewsPojo getNewsById(long newsId) {
		return newsDao.select(newsId);
	}
	
	/**
	 * 根据查询条件，获得新闻资讯的总记录数
	 * 
	 * @param filter 查询条件
	 */
	public int getNewsCount(CmsNewsSearchFilter filter) {
		return newsDao.getNewsCount(filter);
	}
	
	/**
	 * 根据查询条件，获得新闻资讯信息
	 * 
	 * @param filter 查询条件
	 * @return 新闻资讯对象集合，没有记录返回null
	 */
	public List<CmsNewsPojo> getNewsByFilter(CmsNewsSearchFilter filter) {
		return newsDao.getNewsByFilter(filter);
	}
	
	/**
	 * 根据查询条件，获得新闻资讯信息
	 * 
	 * @param newsId 新闻记录id号
	 * @return 删除是否成功
	 */
	public boolean deleteNewsById(long newsId) {
		return newsDao.delete(newsId, newsDao.getVersion(newsId));
	}
	
	
	public void setNewsDao(CmsNewsDao newsDao) {
		this.newsDao = newsDao;
	}
	public void setNewsColumnDao(CmsNewsColumnDao newsColumnDao) {
		this.newsColumnDao = newsColumnDao;
	}
	
}

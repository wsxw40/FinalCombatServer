package com.pearl.o2o.dao.impl.nonjoin;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.SysNotice;


public class SysNoticeDao extends BaseMappingDao {
	
	@SuppressWarnings("unchecked")
	public List<SysNotice> getSysNoticeList()throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("SysNotice.selectAll");
	}
	
	@SuppressWarnings("unchecked")
	public SysNotice getSysNoticeMap(int id) throws DataAccessException{
		return queryMappingBeanById(SysNotice.class, id);
	}	
	
	
	@SuppressWarnings("unchecked")
	public void create(SysNotice sysNotice) throws Exception {
		this.getSqlMapClientTemplate().insert("SysNotice.insert", sysNotice);
	}
	
	@SuppressWarnings("unchecked")
	public void update(SysNotice sysNotice) throws Exception {
		this.getSqlMapClientTemplate().update("SysNotice.update",sysNotice);
	}

	@SuppressWarnings("unchecked")
	public void delete(SysNotice sysNotice) throws Exception {
		this.getSqlMapClientTemplate().delete("SysNotice.delete",sysNotice);
	}	
}
